package com.tmall.service.impl;

import com.tmall.mapper.CartMapper;
import com.tmall.mapper.ProductMapper;
import com.tmall.pojo.Cart;
import com.tmall.pojo.Order;
import com.tmall.pojo.OrderMaster;
import com.tmall.service.CartService;
import com.tmall.util.RandOrderNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    //根据用户ID获取其所有购物车信息
    @Override
    @Transactional
    public List<Cart> getCartById(BigInteger userId) {
        return cartMapper.getCartById(userId);
    }

    // 修改某一个购物车商品的数量
    @Override
    public void setCartCount(BigInteger cartId, BigInteger count) {
        cartMapper.setCartCount(cartId, count);
    }

    // 获取某个用户的购物车信息的数量
    @Override
    @Transactional
    public BigInteger getCartCountById(BigInteger userId) {
        return cartMapper.getCartCountById(userId);
    }

    // 检验用户是否收藏了某一件商品
    @Override
    public Cart findCartItem(BigInteger userId, BigInteger productId) {
        return cartMapper.findCartItem(userId, productId);
    }

    // 用户添加商品到购物车
    @Override
    @Transactional
    public void addToCart(BigInteger userId, BigInteger productId, BigInteger count) {
        // 获取商品剩余数量
        BigInteger number = productMapper.getProductLeft(productId);
        if (number.compareTo(count) < 0) {
            throw new RuntimeException("抱歉!库存不足!");
        }
        cartMapper.addToCart(userId, productId, count);

    }

    // 删除某用户的某一条购物车商品信息
    @Override
    public void deleteCart(BigInteger cartId) {
        cartMapper.deleteCart(cartId);
    }

    // 添加订单
    @Override
    public void getCartProductIds(BigInteger userId, String orderNumber) {
        // 获取订单中的商品id
        BigInteger[] productIds = {};
        while (productIds.length == 0) {
            productIds = cartMapper.getProductIds(orderNumber);
        }
        // 通过product_id查询product中的shop_id #(考虑去重,同一家商店的合并为一个)
        Set<BigInteger> shopIds = cartMapper.getShopIdsByProductIds(productIds);
        // 创建一个总订单
        Order order = new Order();
        Date date = new Date();
        ArrayList<OrderMaster> orderMasterList = new ArrayList<>();
        String orderNumberList = "";
        double money = 0;
        // 通过order_number,shop_id查询该店商品的实际价格
        for (BigInteger shopId : shopIds) {
            double[] productPrices = cartMapper.getProductPrices(orderNumber, shopId);
            // 将上面的结果写入到order_master中
            // 1.为该商家生成一个订单号
            String randOrderNumber = RandOrderNumber.getRandOrderNumber();
            // 2.获取该商家的总金额
            double orderAmount = 0;
            for (double productPrice : productPrices) {
                orderAmount += productPrice;
            }
            // 3.进行数据写入
            cartMapper.addOrderMaster(randOrderNumber,userId,orderAmount,shopId);
            // 获取上述订单
            OrderMaster orderMaster = cartMapper.getOrderMaster(randOrderNumber);
            orderMasterList.add(orderMaster);
            orderNumberList = orderNumberList + "," + randOrderNumber;
            money += orderMaster.getOrderAmount();
        }
        orderNumberList = orderNumberList.substring(1);
        order.setOrderNumberAll(orderNumber)
                .setCreateTime(date)
                .setUpdateTime(date)
                .setOrderMasters(orderMasterList)
                .setOrderNumber(orderNumberList)
                .setMoney(money)
                .setBuyerId(userId);
        // 写入数据到order中
        cartMapper.addOrder(order);
        // return cartMapper.getCartProductIds(userId);
    }

    // 通过产品id获取店铺id
    @Override
    public Set<BigInteger> getShopIdsByProductIds(BigInteger[] productIds) {
        return cartMapper.getShopIdsByProductIds(productIds);
    }

    // 通过order_number,shop_id查询该店商品的实际价格
    @Override
    public double[] getProductPrices(String orderNumber, BigInteger shopId) {
        return cartMapper.getProductPrices(orderNumber, shopId);
    }

    // 写入order_master中
    @Override
    @Transactional
    public void addOrderMaster(String orderNumber, BigInteger userId, double[] productPrices, BigInteger shopId) {
        double orderAmount = 0;
        for (double productPrice : productPrices) {
            orderAmount += productPrice;
        }
        // if (orderAmount != 0)
        cartMapper.addOrderMaster(orderNumber,userId,orderAmount,shopId);
    }

    // 基于订单id查询product_detail的商品id
    @Override
    public BigInteger[] getProductIds(String orderNumber) {
        return cartMapper.getProductIds(orderNumber);
    }

    // 将该订单的数据查询出来
    @Override
    public OrderMaster getOrderMaster(String randOrderNumber) {
        return cartMapper.getOrderMaster(randOrderNumber);
    }

    // 写入数据到order
    @Override
    public void addOrder(Order order) {
        cartMapper.addOrder(order);
    }

    // 清空购物车
    @Override
    @Transactional
    public List<String> deleteCartByUserId(BigInteger userId) {
        // 获取该用户的购物车
        List<Cart> cartProductList = cartMapper.getCartProductIds(userId);
        List<String> productNames = new ArrayList<>();
        // 判断库存是否充足
        for (Cart cart : cartProductList) {
            BigInteger left = productMapper.getProductLeft(cart.getProductId());
            String productName = productMapper.getProductName(cart.getProductId());
            if (left.compareTo(cart.getCount()) < 0) {
                productNames.add(productName);
            }
        }
        cartMapper.deleteCartByUserId(userId);
        return productNames;
    }
}
