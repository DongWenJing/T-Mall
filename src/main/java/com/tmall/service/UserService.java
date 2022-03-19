package com.tmall.service;

import com.tmall.pojo.User;

/**
 * @author R.Yu
 * @date 2022/3/19 13:16
 */
public interface UserService {
    /**
     * 根据前端传过来的用户名和密码进行查询
     * @param user
     * @return User对象
     */
    User selectUserByUP(User user);
}
