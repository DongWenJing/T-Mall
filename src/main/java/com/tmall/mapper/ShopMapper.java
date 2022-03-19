package com.tmall.mapper;

import com.tmall.pojo.Shop;
import org.apache.ibatis.annotations.Insert;

import java.math.BigInteger;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:51
 */
public interface ShopMapper {

    void addUser(Shop shop);

    BigInteger getUserId(String username);
}
