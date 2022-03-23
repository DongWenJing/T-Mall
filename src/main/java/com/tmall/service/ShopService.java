package com.tmall.service;

import com.tmall.pojo.Shop;

import java.math.BigInteger;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:35
 */
public interface ShopService {

    Shop getUserByUsername(String username);

    void shopRegister(Shop shop);

    Shop getShopByUserId(BigInteger userId);

    Shop getShopByShopId(BigInteger shopId);
}
