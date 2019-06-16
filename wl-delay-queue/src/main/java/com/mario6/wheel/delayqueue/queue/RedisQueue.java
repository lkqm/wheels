package com.mario6.wheel.delayqueue.queue;

import com.mario6.wheel.delayqueue.model.Job;
import com.mario6.wheel.delayqueue.queue.bucket.Bucket;
import com.mario6.wheel.delayqueue.queue.pool.JobPool;
import com.mario6.wheel.delayqueue.queue.scheduler.JobScanScheduler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RedisQueue {

    private JobPool jobPool;
    private Bucket bucket;
    private JobScanScheduler jobScanScheduler;

    public void push(Job job) {
        jobPool.push(job);
        bucket.push(job.getId(), job.getTtr());
    }

    public void start() {
        jobScanScheduler.start();
    }

    public void shutdown() {
        jobScanScheduler.shutdown();
    }

}
