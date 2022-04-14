package com.tmall.service.impl;

import com.tmall.mapper.NowtimeMapper;
import com.tmall.service.NowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class NowtimeServiceImpl implements NowtimeService {

    @Autowired
    private NowtimeMapper nowtimeMapper;

    @Override
    public Timestamp getTime() {
        return nowtimeMapper.getTime();
    }

    @Override
    public void setTime(String newTime) {
        nowtimeMapper.setTime(newTime);
    }
}


