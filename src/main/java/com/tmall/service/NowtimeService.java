package com.tmall.service;

import java.sql.Time;
import java.sql.Timestamp;

public interface NowtimeService {
    //Timestamp getTime();

    Timestamp getTime1();

    //修改时间
    void changedTime(String newTime);
}
