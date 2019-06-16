package com.mario6.wheel.delayqueue.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 任务添加请求DTO
 */
@Data
public class JobMsg implements Serializable {

    /**
     * 大分类
     */
    private String topic;

    /**
     * 小分类
     */
    private String subtopic;

    /**
     * 业务主键
     */
    private String bizKey;

    /**
     * 延迟毫秒数
     */
    private Long delay;

    /**
     * 运行时间点
     */
    private Long ttr;

    /**
     * 数据
     */
    private String body;

}
