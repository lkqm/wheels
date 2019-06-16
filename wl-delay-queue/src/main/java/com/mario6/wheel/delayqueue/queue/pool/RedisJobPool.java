package com.mario6.wheel.delayqueue.queue.pool;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.mario6.wheel.delayqueue.model.Job;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于redis的任务池
 */
@Slf4j
@AllArgsConstructor
public class RedisJobPool implements JobPool {

    private String key;
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public void push(Job job) {
        redisTemplate.boundHashOps(key).put(job.getId(), JSONObject.toJSONString(job));
    }

    @Override
    public Job pop(String jobId) {
        String data = redisTemplate.<String, String>boundHashOps(key).get(jobId);
        if (data == null) return null;
        redisTemplate.boundHashOps(key).delete(jobId);

        return parseJobData(data);
    }

    @Override
    public List<Job> pop(List<String> jobIds) {
        List<String> data = redisTemplate.<String, String>boundHashOps(key).multiGet(jobIds);
        List<Job> jobs = new ArrayList<>(data.size());
        for (String json : data) {
            Job job = parseJobData(json);
            if (job != null) jobs.add(job);
        }

        redisTemplate.boundHashOps(key).delete(jobIds);
        return jobs;
    }

    private Job parseJobData(String data) {
        Job job = null;
        try {
            job = JSONObject.parseObject(data, Job.class);
        } catch (JSONException e) {
            log.info("任务池数据获取格式错误", e);
        }
        return job;
    }
}
