package com.mario6.wheel.delayqueue.queue.bucket;

import java.util.List;

public interface Bucket {

    void push(String jobId, long ttr);

    List<String> pop(int size);

}
