package com.tmall.service.impl;

import com.tmall.mapper.ShopMapper;
import com.tmall.pojo.Shop;
import com.tmall.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:36
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    //获取验证商家注册时所需的username
    @Override
    public Shop getUserByUsername(String username) {
        return shopMapper.getUserByUsername(username);
    }
    //商家注册
    @Override
    public void shopRegister(Shop shop) {
        String password = shop.getPassword();
        password =DigestUtils.md5DigestAsHex(password.getBytes());
        shop.setPassword(password);
        
        shopMapper.shopRegister(shop);
    }

    @Override
    public Shop getShopByUserId(BigInteger userId) {
        return shopMapper.getShopByUserId(userId);
    }

    // 通过商家id获取店铺信息
    @Override
    public Shop getShopByShopId(BigInteger shopId) {
        return shopMapper.getShopByShopId(shopId);
    }
}
