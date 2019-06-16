package com.mario6.wheel.delayqueue.queue.bucket;


import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Getter
public class MultipleBucket implements Bucket {

    private String bucketName;
    private int size;
    private RedisTemplate<String, String> redisTemplate;

    private Bucket[] buckets;


    public MultipleBucket(String name, int size, RedisTemplate<String, String> redisTemplate) {
        this.bucketName = name;
        this.size = size;
        this.redisTemplate = redisTemplate;
        this.buckets = new Bucket[this.size];
        for (int i = 0; i < this.size; i++) {
            String subBucketName = bucketName + (i + 1);
            buckets[i] = new SimpleBucket(subBucketName, redisTemplate);
        }
    }

    @Override
    public void push(String jobId, long ttr) {
        int idx = Math.abs(jobId.hashCode() % buckets.length);
        buckets[idx].push(jobId, ttr);
    }

    @Override
    public List<String> pop(int dataSize) {
        throw new UnsupportedOperationException("不支持该方法pop数据, 使用bucket(idx)获取Bucket进行操作");
    }

    public Bucket bucket(int idx) {
        return buckets[idx];
    }

    private String bucketName(String jobId) {
        int idx = jobId.hashCode() % size + 1;
        return bucketName + idx;
    }

}
