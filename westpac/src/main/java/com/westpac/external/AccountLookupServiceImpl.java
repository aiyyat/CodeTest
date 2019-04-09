package com.westpac.external;

import com.westpac.AccountLookupService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public final class AccountLookupServiceImpl implements AccountLookupService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(30);
    private final AtomicInteger inflights = new AtomicInteger(0);
    private final AtomicBoolean alive = new AtomicBoolean(true);

    @Override
    public void lookup(final String requestId, final String clientId, final Callback callback) {
        synchronized (inflights) {
            while (alive.get() && inflights.get() >= 30) {
                try {
                    inflights.wait(100);
                } catch (InterruptedException e) {
                    //
                }
            }
            inflights.incrementAndGet();
        }
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(50));
                try {
                    callback.onResult(requestId, clientId + "-account");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                } finally {
                    synchronized (inflights) {
                        inflights.decrementAndGet();
                        inflights.notifyAll();
                    }
                }
            }
        });
    }

    @Override
    public void shutdown() {
        alive.set(false);
        executorService.shutdown();
    }
}
