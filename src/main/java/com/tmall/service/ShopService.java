package com.tmall.service;

import com.tmall.pojo.Shop;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:35
 */
public interface ShopService {

    Shop getUserByUsername(String username);

    void shopRegister(Shop shop);
}
