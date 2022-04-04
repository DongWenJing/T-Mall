package com.tmall.service.impl;

import com.tmall.mapper.ProductMapper;
import com.tmall.mapper.ShopMapper;
import com.tmall.pojo.OrderMaster;
import com.tmall.pojo.Shop;
import com.tmall.service.ShopService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:36
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ProductMapper productMapper;

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

    //查询商家销售额
    @Override
    public Double getSale(Double shopId) {
        /*Double sale = shopMapper.getSale(shopId);
        if (sale == null) {
            return 0d;
        }
        return sale;*/
        return shopMapper.getSale(shopId);
    }

    //查询商家的销量
    @Override
    public Integer getVolume(Integer shopId) {
        // 通过商家id查询子订单号
        List<String> orderNumbers = shopMapper.getOrderMaster(shopId);
        List<String> oNs = new ArrayList<>();
        // 通过子订单号查询总订单号
        for (String oN : orderNumbers) {
            oNs.add(productMapper.findOrderNumberAll(oN));
        }
        // 统计商家的销量
        Integer volume = null;
        try {
            volume = shopMapper.getVolume(oNs);
        } catch (Exception e) {
            return 0;
        }
        return volume;
    }
}
