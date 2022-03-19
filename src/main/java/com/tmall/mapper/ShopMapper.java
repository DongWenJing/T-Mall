package com.tmall.mapper;

import com.tmall.pojo.Shop;
import org.apache.ibatis.annotations.Insert;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:51
 */
public interface ShopMapper {
    //"INSERT INTO shop(shop_name, username, owner_id) VALUE(#{shopName}, #{username}, #{ownerId})"
    @Insert("inser into shop(shop_name,username,owner_id) value(#{shopName},#{username},#{ownerId})")
    void addUser(Shop shop);
}
