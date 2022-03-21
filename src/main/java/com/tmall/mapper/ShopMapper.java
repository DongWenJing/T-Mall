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

    @Select("select username from shop where username=#{username}")
    Shop getUserByUsername(String username);
    //商户注册
    //@Insert("INSERT INTO `user`(telephone,username,real_name,`password`, role) VALUE(#{telephone},#{username},#{realName},#{password}, 'shop')")
    @Insert("insert into user(telephone,username,real_name,password,role) value(#{telephone},#{username},#{realName},#{password},shop)")
    void shopRegister(Shop shop);
}
