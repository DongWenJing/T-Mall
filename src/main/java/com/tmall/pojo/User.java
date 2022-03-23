package com.tmall.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.sql.Timestamp;
@Data
@Accessors(chain = true)
public class User {
    //用户id
    private BigInteger userId;
    //用户名
    private String username;
    //登录密码
    private String password;
    //真实姓名
    private String realName;
    // 客户：user, 管理员：admin，商家：shop
    private String role;
    // 用户状态，0禁止登录，1可以登陆
    private int status;
    // 用户头像
    private String avatar;
    //年龄
    private int age;
    //性别
    private String sex;
    //地址
    private String address;
    //电话号码
    private String telephone;
    // 用户剩余资金，用于购买商品
    private double money;
    // 账号注册时间
    private Timestamp createTime;
    //电子邮件
    private String email;
    // 软删除，1表示删除，0表示未删除
    private Integer isDeleted;

}
