package com.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Nowtime {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    // private Timestamp newTime;
    private Timestamp newTime;
    // private Timestamp updateTime;
    private Timestamp updateTime;

}