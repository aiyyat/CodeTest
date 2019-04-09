package com.westpac.external;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.westpac.LegacyService;
import com.westpac.Request;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public final class LegacyServiceImpl implements LegacyService {

    private final Timer latency;
    private final ConcurrentHashMap<RequestAndAccount, Boolean> requests;
    private final AtomicInteger counter;

    public LegacyServiceImpl(MetricRegistry metricRegistry) {
        latency = metricRegistry.timer("Request latency");
        requests = new ConcurrentHashMap<>();
        counter = new AtomicInteger(0);
    }

    public void execute(Request request, String accountId) {
        final long timestamp = request.getTimestamp();
        if (timestamp > 0) latency.update(System.currentTimeMillis() - timestamp, TimeUnit.MILLISECONDS);
        final int value = counter.incrementAndGet();
        if (value > 25) throw new RuntimeException("Legacy Service SLA breached");
        final RequestAndAccount requestAndAccount = new RequestAndAccount(request, accountId);
        requests.put(requestAndAccount, Boolean.TRUE);
        final int receivedRequestsCount = getReceivedRequestsCount();
        if (receivedRequestsCount % 200 == 0) System.out.println("Legacy Service received " + receivedRequestsCount + " requests");
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(20));
        counter.decrementAndGet();
    }

    public int getReceivedRequestsCount() {
        return requests.size();
    }
    public void resetRequestCount() {
        requests.clear();
    }

    public boolean hasProcessedTrade(Request request) {
        return requests.containsKey(new RequestAndAccount(request, request.getClientId() + "-account"));
    }

    private final class RequestAndAccount {
        private final Request request;
        private final String account;

        RequestAndAccount(Request request, String account) {
            this.request = request;
            this.account = account;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RequestAndAccount)) return false;

            RequestAndAccount that = (RequestAndAccount) o;

            if (request != null ? !request.equals(that.request) : that.request != null) return false;
            return account != null ? account.equals(that.account) : that.account == null;

        }

        @Override
        public int hashCode() {
            int result = request != null ? request.hashCode() : 0;
            result = 31 * result + (account != null ? account.hashCode() : 0);
            return result;
        }
    }

}
