package com.mario6.wheel.delayqueue;

import com.mario6.wheel.delayqueue.queue.RedisQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DelayQueueApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DelayQueueApplication.class, args);
    }

    @Autowired
    private RedisQueue queue;

    @Override
    public void run(String... args) throws Exception {
        queue.start();
    }
}
