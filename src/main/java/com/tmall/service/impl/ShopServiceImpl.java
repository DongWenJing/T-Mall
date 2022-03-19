package com.tmall.service.impl;

import com.tmall.mapper.ShopMapper;
import com.tmall.pojo.Shop;
import com.tmall.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:36
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Override
    public void addUser(Shop shop) {
        //通过用户名获取user表中的userId值
     BigInteger userId = shopMapper.getUserId(shop.getUsername());
     shop.setOwnerId(userId);
        shopMapper.addUser(shop);
    }
}
