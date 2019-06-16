package com.mario6.wheel.delayqueue.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.mario6.wheel.delayqueue.config.DelayQueueProperties.PREFIX;

/**
 * 延时队列服务端配置
 */
@ConfigurationProperties(prefix = PREFIX)
@Data
public class DelayQueueProperties {
    public static final String PREFIX = "delayqueue";

    /**
     * 相关redis前缀
     */
    private String redisKeyPrefix = "delay-queue";

    /**
     * bucket数量
     */
    private int bucketNumber = 10;

    /**
     * 一次扫描bucket中数据大小
     */
    private int bucketScanSize = 10;

}
