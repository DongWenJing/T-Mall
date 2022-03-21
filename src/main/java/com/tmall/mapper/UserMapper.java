package com.tmall.mapper;

import com.tmall.pojo.User;

import java.math.BigInteger;

/**
 * @author R.Yu
 * @date 2022/3/19 12:41
 */
public interface UserMapper {
    User selectUserByUP(User user);

    User selectUserById(BigInteger userId);
}
