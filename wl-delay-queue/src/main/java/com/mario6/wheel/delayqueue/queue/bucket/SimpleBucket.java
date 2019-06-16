package com.mario6.wheel.delayqueue.queue.bucket;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class SimpleBucket implements Bucket {

    private String bucketName;
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public void push(String jobId, long ttr) {
        redisTemplate.boundZSetOps(bucketName).add(jobId, ttr);
    }

    @Override
    public List<String> pop(int size) {
        RedisZSetCommands.Range range = RedisZSetCommands.Range.range().lte(System.currentTimeMillis());
        RedisZSetCommands.Limit limit = RedisZSetCommands.Limit.limit().count(size);

        Set<String> jobIds = redisTemplate.boundZSetOps(bucketName).rangeByLex(range, limit);
        redisTemplate.boundZSetOps(bucketName).remove(jobIds);

        return new ArrayList<>(jobIds);
    }
}
