package com.tmall.service;

import com.tmall.pojo.Password;
import com.tmall.pojo.User;
import com.tmall.vo.Page;

import java.math.BigInteger;
import java.util.List;

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

    /**
     * 根据登录用户的Id获取展示个人信息
     * @param userId
     * @return
     */
    User getUserById(BigInteger userId);

    //修改用户信息
    void updateUserById(User user);

    Page<User> findUserList(Integer pageNum, Integer pageSize, String key);
    //修改密码
    void setPassword(Password password);
    //获取原始密码
    String getOldPassword(BigInteger userId);

    // 查询商家数量分页显示
    List<User> findShopperList(Integer pageNum, Integer pageSize, String key);

    // 根据key查询商家数量
    Integer countShopper(String key);
    //充值
    void addRecharge(BigInteger userId, double money);

    Double getRecharge(BigInteger userId, double money);
}
