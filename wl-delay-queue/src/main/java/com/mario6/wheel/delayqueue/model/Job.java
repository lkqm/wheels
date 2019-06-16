package com.mario6.wheel.delayqueue.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Job implements Serializable {

    private String id;

    private String topic;

    private String subtopic;

    private String bizKey;

    private Long ttr;

    private String body;

    private Integer status;

    private Date createTime;
}
