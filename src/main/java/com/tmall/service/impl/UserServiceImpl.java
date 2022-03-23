package com.tmall.service.impl;

import com.tmall.mapper.UserMapper;
import com.tmall.pojo.Password;
import com.tmall.pojo.User;
import com.tmall.service.UserService;
import com.tmall.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.Date;

import java.math.BigInteger;
import java.util.List;

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

    /**
     * 分页查询所有用户信息
     * @param pageNum
     * @param pageSize
     * @param key
     * @return
     */
    @Override
    public Page<User> findUserList(Integer pageNum, Integer pageSize, String key) {
        pageNum = (pageNum - 1) * pageSize;
        //获取查询到的数据
        List<User> users = userMapper.findUserList(pageNum,pageSize,key);
        //总记录条数
        Integer total = userMapper.countUser(key);
        //封装查询到的数据
        Page<User> page = new Page();
        page.setTotal(total).setData(users).setPageSize(pageSize).setPageNum(pageNum);
        return page;
    }

    /**
     * 获取数据库中原始密码
     * @param userId
     * @return
     */
    public String getOldPassword(BigInteger userId) {
        String oldPassword = this.userMapper.getOldPassword(userId);
        return oldPassword;
    }

    /**
     * 修改用户密码,加密后存储到数据库
     * @param password
     */
    public void setPassword(Password password) {
        String newPassword = password.getNewPassword();
        newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        password.setNewPassword(newPassword);
        this.userMapper.setPassword(password);
    }
}
