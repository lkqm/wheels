package com.mario6.wheel.delayqueue.queue.scheduler;


import com.mario6.wheel.delayqueue.queue.bucket.Bucket;
import com.mario6.wheel.delayqueue.queue.bucket.MultipleBucket;
import com.mario6.wheel.delayqueue.queue.pool.JobPool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务扫描定时
 */
@Slf4j
@Data
public class JobScanScheduler {

    private JobPool jobPool;
    private MultipleBucket multipleBucket;

    private ScheduledExecutorService executor;

    public JobScanScheduler(JobPool jobPool, MultipleBucket multipleBucket) {
        this.jobPool = jobPool;
        this.multipleBucket = multipleBucket;
    }

    public void start() {
        int bucketSize = multipleBucket.getSize();
        this.executor = Executors.newScheduledThreadPool(bucketSize);
        for (int i = 0; i < bucketSize; i++) {
            Bucket bucket = multipleBucket.bucket(i);
            JobScanTask task = new JobScanTask(jobPool, bucket, 10);
            executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
        }
        log.info("启动Job定时处理任务共{}个", bucketSize);
    }

    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
            executor = null;
            log.info("关闭Job定时任务");
        }
    }

}
