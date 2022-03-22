package com.tmall.mapper;

import com.tmall.pojo.User;

import java.math.BigInteger;

/**
 * @author R.Yu
 * @date 2022/3/19 12:41
 */
public interface UserMapper {

    User selectUserByUP(User user);

    //通过用户Id显示查询数据
    User selectUserById(BigInteger userId);
    //修改用户信息
    void updateUserById(User user);
}
