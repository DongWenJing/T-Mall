package com.tmall.mapper;

import com.tmall.pojo.Cart;
import com.tmall.pojo.Order;
import com.tmall.pojo.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

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
    // 获取该用户的购物车产品的id
    BigInteger[] getCartProductIds(BigInteger userId);

    // 通过产品id获取店铺id
    Set<BigInteger> getShopIdsByProductIds(BigInteger[] productIds);

    // 通过order_number,shop_id查询该店商品的实际价格
    double[] getProductPrices(@Param("orderNumber") String orderNumber,
                              @Param("shopId") BigInteger shopId);

    // 写入order_master中
    void addOrderMaster(@Param("orderNumber") String orderNumber,
                        @Param("userId") BigInteger userId,
                        @Param("orderAmount") double orderAmount,
                        @Param("shopId") BigInteger shopId);

    // 基于订单id查询product_detail的商品id
    BigInteger[] getProductIds(String orderNumber);

    // 将该订单的数据查询出来
    OrderMaster getOrderMaster(String randOrderNumber);

    // 写入数据到order
    void addOrder(Order order);

    // 清空购物车
    void deleteCartByUserId(BigInteger userId);
}
