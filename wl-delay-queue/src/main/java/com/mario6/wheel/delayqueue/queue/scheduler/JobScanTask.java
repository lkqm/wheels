package com.mario6.wheel.delayqueue.queue.scheduler;

import com.mario6.wheel.delayqueue.model.Job;
import com.mario6.wheel.delayqueue.queue.bucket.Bucket;
import com.mario6.wheel.delayqueue.queue.pool.JobPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
class JobScanTask implements Runnable {

    private JobPool jobPool;
    private Bucket bucket;
    private int batchSize;

    @Override
    public void run() {
        log.info("开始扫描{}", bucket);

        List<String> jobIds = bucket.pop(batchSize);
        List<Job> jobs = jobPool.pop(jobIds);
        for (Job job : jobs) {
            System.out.println("控制台消费消息:" + job);
        }
        log.info("完毕扫描{}", bucket);
    }
}
