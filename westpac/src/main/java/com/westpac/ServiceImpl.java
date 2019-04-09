package com.westpac;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Fixed Me.
 */
public class ServiceImpl implements Service {
    private final AccountLookupService accountLookupService;
    private final LegacyService legacyService;

    public ServiceImpl(AccountLookupService accountLookupService, LegacyService legacyService) {
        this.accountLookupService = accountLookupService;
        this.legacyService = legacyService;
    }

    final HashMap<String, AccountLookupServiceCacheEntry> cache = new HashMap<>(); // Pretend Cache
    ExecutorService executorService = Executors.newFixedThreadPool(24); // Be safe; breaks at > 25

    @Override
    public void submit(final Request request) {
        final AccountLookupServiceCacheEntry entry = cache.getOrDefault(request.getClientId(), new AccountLookupServiceCacheEntry());
        if (entry.getCachedTime() == null || (((Calendar.getInstance().getTimeInMillis() - entry.getCachedTime()) / 1000.0) > 10)) {
            accountLookupService.lookup(request.getId(), request.getClientId(), new AccountLookupService.Callback() {
                @Override
                public void onResult(String id, String accountId) {
                    Long lookupTime = Calendar.getInstance().getTimeInMillis();
                    entry.setAccountId(accountId);
                    entry.setCachedTime(lookupTime);
                    cache.put(request.getClientId(), entry); // to make all the difference
                }
            });
        }
        executorService.submit(new Runnable() {
            public void run() {
                legacyService.execute(request, entry.accountId);
            }
        });
    }

    @Override
    public void shutdown() {
        //Not currently used
    }
}

class AccountLookupServiceCacheEntry {
    Long cachedTime;
    String accountId;

    public Long getCachedTime() {
        return cachedTime;
    }

    public void setCachedTime(Long cachedTime) {
        this.cachedTime = cachedTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
