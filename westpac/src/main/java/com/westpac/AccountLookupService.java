package com.westpac;

public interface AccountLookupService {


    interface Callback {
        /**
         * Called when lookup is complete
         *
         * @param id        request id
         * @param accountId account
         */
        void onResult(String id, String accountId);
    }

    /**
     * - Thread-safe
     * - Underlying thread-pool size: 30 threads
     * - Blocks if no available threads in the pool
     * - Expected avg lookup latency (excluding blocking pool wait time): 50ms
     * - Results can be cached for up to 10 seconds (total client/account count does not exceed 100 entries)
     *
     * @param requestId unique arbitrary request id
     * @param clientId  client id
     * @param callback  callback
     */
    void lookup(String requestId, String clientId, Callback callback);

    void shutdown();
}
