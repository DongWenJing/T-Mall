package com.tmall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.sql.Timestamp;
import java.util.Date;

@Mapper
public interface NowtimeMapper {


    Timestamp getTime();


    void setTime(String newTime);
}
