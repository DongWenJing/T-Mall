package com.tmall.service.impl;

import com.tmall.mapper.NowtimeMapper;
import com.tmall.pojo.Nowtime;
import com.tmall.service.NowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class NowtimeServiceImpl implements NowtimeService {

    @Autowired
    private NowtimeMapper nowtimeMapper;

//获取时间
    @Override
    public Timestamp getTime1() {
        Date time1 = nowtimeMapper.getTime1();
        Timestamp timestamp = new Timestamp(time1.getTime());
        return timestamp;

    }

    @Override
    public void changedTime(String newTime) {
        nowtimeMapper.ChangedTime(newTime);
    }


}


