package com.tmall.service.impl;

import com.tmall.mapper.ShopMapper;
import com.tmall.pojo.Shop;
import com.tmall.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Shop getShopByUserId(BigInteger userId) {
        return shopMapper.getShopByUserId(userId);
    }

    // 通过商家id获取店铺信息
    @Override
    public Shop getShopByShopId(BigInteger shopId) {
        return shopMapper.getShopByShopId(shopId);
    }

    // 获取用户id
    @Override
    public BigInteger getUserId(String username) {
        return shopMapper.getUserId(username);
    }

    // 完成商家注册
    @Override
    @Transactional
    public void shopRegister(Shop shop) {
        shopMapper.shopRegister(shop);
    }

    // 通过产品id获取商家id
    @Override
    public BigInteger getShopIdByProductId(BigInteger productId) {
        return shopMapper.getShopByProductId(productId);
    }
}
