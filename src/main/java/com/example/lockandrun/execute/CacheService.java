package com.example.lockandrun.execute;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Component
public class CacheService {
    private final JedisPool jedisPool;

    public CacheService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Object get(String key) {
        return jedisPool.getResource().get(key);
    }

    public Long increment(String key, int seconds) {
        try(Jedis jedis = jedisPool.getResource()) {
            final Long incr = jedis.incr(key);
            jedis.expire(key, seconds);
            return incr;
        }catch (Exception e) {
            return 0L;
        }
    }
}
