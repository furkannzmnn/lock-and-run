package com.example.lockandrun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;

@SpringBootApplication
public class LockAndRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(LockAndRunApplication.class, args);
    }

    @Bean()
    public JedisPool jedisPool() {
        return new JedisPool("localhost");
    }
}
