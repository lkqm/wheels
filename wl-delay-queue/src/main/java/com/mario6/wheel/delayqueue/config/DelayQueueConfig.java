package com.mario6.wheel.delayqueue.config;

import com.mario6.wheel.delayqueue.queue.bucket.MultipleBucket;
import com.mario6.wheel.delayqueue.queue.pool.JobPool;
import com.mario6.wheel.delayqueue.queue.pool.RedisJobPool;
import com.mario6.wheel.delayqueue.queue.scheduler.JobScanScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableConfigurationProperties(DelayQueueProperties.class)
public class DelayQueueConfig {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    DelayQueueProperties properties;

    @Bean
    public JobPool jobPool() {
        String keyPrefix = properties.getRedisKeyPrefix();
        String key = keyPrefix + ":jobs";
        return new RedisJobPool(key, redisTemplate);
    }

    @Bean
    public MultipleBucket multipleBucket() {
        String keyPrefix = properties.getRedisKeyPrefix();

        String key = keyPrefix + ":bucket";
        int bucketNumber = properties.getBucketNumber();
        return new MultipleBucket(key, bucketNumber, redisTemplate);
    }

    @Bean
    public JobScanScheduler jobScanScheduler(JobPool jobPool, MultipleBucket multipleBucket) {
        return new JobScanScheduler(jobPool, multipleBucket);
    }

}
