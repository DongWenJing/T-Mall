package com.tmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:36
 */
@Data
public class Shop implements Serializable {
    private static final long serialVersionUID = -3183898886361420155L;
    //商铺ID
    private BigInteger shopId;
    //所有者ID
    private BigInteger ownerId;
    //店铺名
    private String shopName;
    //所有者名字
    private String username;
    //实际拥有者名字
    private String realName;
    //创建时间
    // private Timestamp createTime;
    private Date createTime;
    //店铺收益
    private Double income;
    //电话号
    private String telephone;
    //密码
    private String password;
}
