package com.mario6.wheel.delayqueue.controller;

import com.mario6.wheel.delayqueue.constant.JobStatus;
import com.mario6.wheel.delayqueue.model.Job;
import com.mario6.wheel.delayqueue.model.JobMsg;
import com.mario6.wheel.delayqueue.queue.RedisQueue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 任务相关
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private RedisQueue redisQueue;

    private static final String KEY_PREFIX = "delayqueue:";


    @PostMapping("/push")
    public String push(@RequestBody JobMsg jobMsg) {
        if (jobMsg.getTtr() == null) {
            jobMsg.setTtr(System.currentTimeMillis() + jobMsg.getDelay());
        }
        Job job = new Job();
        BeanUtils.copyProperties(jobMsg, job);
        job.setId(UUID.randomUUID().toString());
        job.setStatus(JobStatus.DELAY.getValue());
        redisQueue.push(job);
        return job.getId();
    }

}
