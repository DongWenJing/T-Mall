package com.tmall.service.impl;

import com.tmall.mapper.ShopMapper;
import com.tmall.pojo.Shop;
import com.tmall.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        shop.setOwnerId(shop.getOwnerId());
        shop.setShopName(shop.getShopName());
        //shop.setIncome(shop.getIncome());
        shop.setPassword(shop.getPassword());
        shop.setRealName(shop.getRealName());
        shop.setTelephone(shop.getTelephone());
        shopMapper.addUser(shop);
    }
}
