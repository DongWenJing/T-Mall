package com.tmall.service;

import com.tmall.pojo.Order;
import com.tmall.pojo.OrderDetail;
import com.tmall.pojo.OrderMaster;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/23 14:44
 */
public interface OrderService {
    List<Order> findOrderById(BigInteger buyerId);

    List<OrderMaster> findByPage(int offset, Integer pageSize, BigInteger shopId);

    Integer getCountByShopId(BigInteger shopId);

    void sendByOrderNumber(String orderNumber,BigInteger shopId);

    Integer getOrderStatus(String orderNumber);

    void cancel(String orderNumber,BigInteger shopId);

    void deleteOrderMaster(OrderMaster orderMaster);

    void addOrderDetail(OrderDetail orderDetail);

    BigInteger getCount(BigInteger userId);

    // 用户取消订单
    void cancel1(String orderNumberAll);

    // 用户删除订单(实现软删除)
    void deleteOrderFalse(String orderNumberAll);

    // 将订单设置为待收货状态(1标识待收货)
    void updateOrderStatus(String orderNumberAll);
}
