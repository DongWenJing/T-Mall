package com.tmall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.sql.Timestamp;
import java.util.Date;

@Mapper
public interface NowtimeMapper {


    //获取时间
   Date getTime1();

    //修改时间
    void ChangedTime(String newTime);
}
