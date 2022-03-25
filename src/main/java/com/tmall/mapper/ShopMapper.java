package com.tmall.mapper;

import com.tmall.pojo.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:51
 */
public interface ShopMapper {

    Shop getShopByUserId(BigInteger userId);

    Shop getShopByShopId(BigInteger shopId);

    // 完成商家注册
    void shopRegister(Shop shop);

    // 获取用户id
    BigInteger getUserId(String username);

    // 修改店铺名字
    void updateShopName(Shop shop);
}
