package com.tmall.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public interface NowtimeService {

    //获取时间
    Timestamp getTime();

    void setTime(String newTime);
}
