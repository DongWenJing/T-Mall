package com.tmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = 3924211568318787254L;
    private Integer id;
    // private Timestamp createTime;
    private Date createTime;
    // private Timestamp updateTime;
    private Date updateTime;
    private String text;
}
