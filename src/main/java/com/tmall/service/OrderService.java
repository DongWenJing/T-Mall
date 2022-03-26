package com.tmall.service;

import com.tmall.pojo.OrderDetail;
import com.tmall.pojo.OrderMaster;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/23 14:44
 */
public interface OrderService {
    List<OrderMaster> findOrderById(BigInteger buyerId);

    List<OrderMaster> findByPage(int offset, Integer pageSize, BigInteger shopId);

    Integer getCountByShopId(BigInteger shopId);

    void sendByOrderNumber(String orderNumber);

    Integer getOrderStatus(String orderNumber);

    void cancel(String orderNumber);


    void addOrderMaster(OrderMaster orderMaster);

    void addOrderDetail(OrderDetail orderDetail);

    BigInteger getCount(BigInteger userId);
}
