package com.tmall.mapper;

import com.tmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface CartMapper {
    // 获取某个用户的所有购物车信息
    List<Cart> getCartById(BigInteger userId);

    // 修改某一个购物车商品的数量
    void setCartCount(@Param("cartId") BigInteger cartId,
                      @Param("count") BigInteger count);

    // 获取某个用户的购物车信息的数量
    BigInteger getCartCountById(BigInteger userId);

    // 检验用户是否了添加了购物车
    Cart findCartItem(@Param("userId") BigInteger userId,
                      @Param("productId") BigInteger productId);

    // 用户添加商品到购物车
    void addToCart(@Param("userId") BigInteger userId,
                   @Param("productId") BigInteger productId,
                   @Param("count") BigInteger count);

    // 删除某用户的某一条购物车商品信息
    void deleteCart(BigInteger cartId);

    // 清空购物车
    void deleteAll(BigInteger userId);
}
