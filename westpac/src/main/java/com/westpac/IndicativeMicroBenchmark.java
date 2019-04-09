package com.westpac;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.westpac.external.AccountLookupServiceImpl;
import com.westpac.external.LegacyServiceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class IndicativeMicroBenchmark {

    private final int MaxClientIds = 100;

    private MetricRegistry metricRegistry;
    private Timer submitLatency;
    private Service service;
    private LegacyServiceImpl legacyImpl;
    private AccountLookupServiceImpl accountLookupImpl;

    private IndicativeMicroBenchmark() {
        metricRegistry = new MetricRegistry();
        submitLatency = metricRegistry.timer("Submit latency");
        legacyImpl = new LegacyServiceImpl(metricRegistry);
        accountLookupImpl = new AccountLookupServiceImpl();
        service = new ServiceImpl(accountLookupImpl, legacyImpl);
    }

    private void execute(Request request) {
        final Timer.Context time = submitLatency.time();
        service.submit(request);
        time.stop();
    }

    private void waitForAll(int expected) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 30000 && legacyImpl.getReceivedRequestsCount() < expected) {
            LockSupport.parkNanos(MILLISECONDS.toNanos(200));
        }
        if (legacyImpl.getReceivedRequestsCount() < expected) throw new RuntimeException("Service did not receive all expected requests");
    }


    private void run() throws InterruptedException {
        warmUp();
        Thread.sleep(1000);
        legacyImpl.resetRequestCount();
        int cnt = 1000;
        final int batchSize = 20;
        int threadCount = 10;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount, new ThreadFactory() {
            private AtomicInteger threadCnt = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r, "Thread " + threadCnt.incrementAndGet());
                thread.setDaemon(true);
                return thread;
            }
        });
        for (int i = 0; i < cnt / batchSize; i++) {
            final int batchN = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Submitting " + batchSize + " requests from " + Thread.currentThread().getName());
                    for (int k = 0; k < batchSize; k++) {
                        int requestId = batchN * batchSize + k;
                        try {
                            execute(new Request("id" + requestId, generateClientId(requestId)));
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.exit(-1);
                        }
                    }
                }
            });
            // queue 1000 messages/sec
            Thread.sleep(batchSize);
        }
        executorService.shutdown();
        waitForAll(cnt);
        if (!executorService.awaitTermination(30, TimeUnit.SECONDS))
            throw new RuntimeException("Executor termination timeout");
        accountLookupImpl.shutdown();
        service.shutdown();
        System.out.println("Finished successfully");
        report();
    }

    private void warmUp() {
        System.out.println("Warming up...");
        for (int i = 0; i < MaxClientIds; i++) {
            service.submit((new Request("id" + i, generateClientId(i), 0)));
        }
    }


    private String generateClientId(int counter) {
        return "client" + (counter % MaxClientIds);
    }

    private void report() {
        ConsoleReporter cr = ConsoleReporter.forRegistry(metricRegistry)
                .outputTo(System.out)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .convertRatesTo(TimeUnit.SECONDS)
                .build();
        cr.report();
    }

    public static void main(String[] args) throws InterruptedException {
        new IndicativeMicroBenchmark().run();
    }

}