package com.mario6.wheel.delayqueue.queue.pool;

import com.mario6.wheel.delayqueue.model.Job;

import java.util.List;

public interface JobPool {

    /**
     * 存入
     */
    void push(Job job);

    /**
     * 弹出
     */
    Job pop(String jobId);

    /**
     * 批量弹出
     */
    List<Job> pop(List<String> jobIds);
}
