package com.tmall.mapper;

import com.tmall.pojo.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoticeMapper {

    //获取公告
    Notice findNotice();


    //保存公告
    void setNotice(Notice notice);
}
