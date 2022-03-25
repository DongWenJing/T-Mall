package com.tmall.service;

import com.tmall.pojo.Password;
import com.tmall.pojo.Shop;
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
     * 根据前端传过来的用户名和密码进行查询信息登录
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
    //分页显示用户信息
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

    BigInteger check(BigInteger userId, BigInteger productId);

    // 检查用户名是否重复
    String getCheckUsername(String username);

    // 商户注册
    void shopRegister(Shop shop);

    // 用户注册
    void userRegister(User user);
    //更新用户权限状态
    void updateUserStatus(BigInteger userId, BigInteger status);

    // 更新账户信息
    void updateAccountInfo(User user);
    //新增用户
    void addUser(User user);

    String findUserName(String username);

    //删除用户
    void deleteById(BigInteger userId);



    User getUserByOrderNumber(String orderNumber);

    // 获取店铺信息
    Shop getShopInfo(BigInteger userId);

    // 重置账户密码
    void resetPassword(User user);


}
