package com.example.lockandrun.service;

import com.example.lockandrun.execute.CacheService;
import com.example.lockandrun.execute.LockExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class PaymentService {

    private static final String PAYMENT_CACHE_KEY = "payment";
    private final JedisPool jedisPool;
    private final CacheService cacheService;
    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);

    public PaymentService(JedisPool jedisPool, CacheService cacheService) {
        this.jedisPool = jedisPool;
        this.cacheService = cacheService;
    }


    public String pay(String paymentId) {
       return new LockExecutor<>(2, paymentId, cacheService) {
           @Override
           public String process() {
               LOGGER.info("Processing payment: {}", paymentId);
               return "payment success";
           }
       }.run(PAYMENT_CACHE_KEY + paymentId);
    }

    public String getPayment(String paymentId) {
        Jedis jedis = jedisPool.getResource();
        String payment = jedis.get(PAYMENT_CACHE_KEY + paymentId);
        jedis.close();
        return payment;
    }



}
