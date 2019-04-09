package com.westpac;

public interface LegacyService {
    /**
     * - SLA: Must not exceed 25 concurrent calls.
     * - Expected latency: 20 ms
     *
     * @param request   request
     * @param accountId account id
     */
    void execute(Request request, String accountId);
}
