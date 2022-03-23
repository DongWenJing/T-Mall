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
    Like findById(@Param("productId") BigInteger productId,
                  @Param("userId") BigInteger userId);

    // 用户添加收藏
    void save(@Param("productId") BigInteger productId,
              @Param("userId") BigInteger userId);

    // 用户删除某一天收藏夹商品
    void delete(BigInteger userId);

    // 获取用户的收藏商品
    List<Like> findAll(BigInteger userId);

    // 获取用户收藏商品的总数
    BigInteger counts(BigInteger userId);

}
