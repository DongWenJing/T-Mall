package com.tmall.service.impl;

import com.tmall.mapper.UserMapper;
import com.tmall.pojo.User;
import com.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.Date;

import java.math.BigInteger;

/**
 * @author R.Yu
 * @date 2022/3/19 13:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUP(User user) {
        String password = user.getPassword();
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        User resultUser = userMapper.selectUserByUP(user);
        if (resultUser == null) {
            return null;
        }
        return resultUser;
    }

    /**
     * 根据登录用户的Id获取展示个人信息
     * @param userId
     * @return
     */
    @Override
    public User getUserById(BigInteger userId) {
      User user = userMapper.selectUserById(userId);
        return user;
    }

    /**
     *修改个人信息
     * @param user
     */
    @Override
    public void updateUserById(User user) {
       /* Date date = new Date();
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        user.setCreateTime(timestamp);*/
        userMapper.updateUserById(user);
    }
}
