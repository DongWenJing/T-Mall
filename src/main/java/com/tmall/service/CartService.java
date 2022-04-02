package com.tmall.service;

import com.tmall.pojo.Cart;
import com.tmall.pojo.Order;
import com.tmall.pojo.OrderMaster;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public interface CartService {
    List<Cart> getCartById(BigInteger userId);

    void setCartCount(BigInteger cartId, BigInteger count);

    BigInteger getCartCountById(BigInteger userId);

    Cart findCartItem(BigInteger userId, BigInteger productId);

    void addToCart(BigInteger userId, BigInteger productId, BigInteger count);

    void deleteCart(BigInteger cartId);

    // 写订单
    void getCartProductIds(BigInteger userId, String orderNumber);

    // 通过产品id获取店铺id
    Set<BigInteger> getShopIdsByProductIds(BigInteger[] productIds);

    // 通过order_number,shop_id查询该店商品的实际价格
    double[] getProductPrices(String orderNumber, BigInteger shopId);

    void addOrderMaster(String orderNumber, BigInteger userId, double[] productPrices, BigInteger shopId);

    // 基于订单id查询product_detail的商品id
    BigInteger[] getProductIds(String orderNumber);

    // 将该订单的数据查询出来
    OrderMaster getOrderMaster(String randOrderNumber);

    // 写入数据到order
    void addOrder(Order order);

    // 清空购物车
    List<String> deleteCartByUserId(BigInteger userId);
}

