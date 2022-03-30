package com.tmall.mapper;

import com.tmall.pojo.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

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

    // 通过产品id获取商家id
    BigInteger getShopByProductId(BigInteger productId);

    Double getSale(Double shopId);

    BigInteger getShopId(BigInteger userId);

    // 统计商家的销量
    Integer getVolume(List<String> oNs);

    // 通过商家id查询子订单号
    List<String> getOrderMaster(Integer shopId);
}
