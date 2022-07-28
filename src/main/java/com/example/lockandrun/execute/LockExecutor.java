package com.example.lockandrun.execute;

public abstract class LockExecutor<T>{
    private int cacheTtl;
    private T defaultValue;
    private CacheService cacheClient;

    protected LockExecutor(int cacheTtl, T defaultValue, CacheService cacheClient) {
        this.cacheTtl = cacheTtl;
        this.defaultValue = defaultValue;
        this.cacheClient = cacheClient;
    }

    public abstract T process();
    public T run(String key) {
        final Long increment = cacheClient.increment(key, cacheTtl);
        if (increment > 1) {
            System.out.println("Cache hit: " + key);
            return defaultValue;
        }
        return process();
    }
}
