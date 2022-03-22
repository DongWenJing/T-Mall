package com.tmall.mapper;

import com.tmall.pojo.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

public interface LikeMapper {
    // 检验用户是否收藏了某个商品
//    @Select("SELECT * FROM `like` WHERE product_id=#{productId} AND user_id=#{userId}")
    Like findById(@Param("productId") BigInteger productId,
                  @Param("userId") BigInteger userId);


    // 用户添加收藏
//    @Insert("INSERT INTO `like`(product_id, user_id) VALUE(#{productId}, #{userId})")
    void save(@Param("productId") BigInteger productId,
              @Param("userId") BigInteger userId);
}
