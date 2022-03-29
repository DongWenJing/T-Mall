package com.tmall.service;

import com.tmall.pojo.Shop;

import java.math.BigInteger;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:35
 */
public interface ShopService {

    // 通过用户id获取商家
    Shop getShopByUserId(BigInteger userId);

    // 通过商家id获取商家信息
    Shop getShopByShopId(BigInteger shopId);

    // 获取用户的id
    BigInteger getUserId(String username);

    // 完成注册商家
    void shopRegister(Shop shop);

    // 通过产品id获取商家id
    BigInteger getShopIdByProductId(BigInteger productId);

    Double getSale(Double shopId);

    Integer getVolume(Integer shopId);
}
