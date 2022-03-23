package com.tmall.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Notice {
    private Integer id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String text;
}
