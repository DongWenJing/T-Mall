package com.tmall.mapper;

import com.tmall.pojo.RoleCount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TheuserofMapper {

    //用户组成
    RoleCount getRoleCount();
}
