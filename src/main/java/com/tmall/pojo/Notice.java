package com.tmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = 3924211568318787254L;
    private Integer id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String text;
}
