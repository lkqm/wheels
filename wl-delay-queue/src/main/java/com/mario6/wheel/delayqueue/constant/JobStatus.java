package com.mario6.wheel.delayqueue.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态
 */
@AllArgsConstructor
@Getter
public enum JobStatus {
    DELAY(1), RESERVED(2), FINISH(3), DEL(-1);

    private int value;


}
