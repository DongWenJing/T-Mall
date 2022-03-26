package com.tmall.mapper;

import com.tmall.pojo.OrderDetail;
import com.tmall.pojo.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/23 14:34
 */
public interface OrderMapper {
    // 获取某用户的全部订单信息
    List<OrderMaster> findOrderById(BigInteger buyerId);

    //分页查询商家的订单信息
    List<OrderMaster> findByPage(@Param("offset") int offset,
                                 @Param("pageSize") Integer pageSize,
                                 @Param("shopId") BigInteger shopId);
    //查询订单条数
    Integer getCountByShopId(BigInteger shopId);

    //修改订单状态
    void sendByOrderNumber(String orderNumber);

    //获取订单状态
    Integer getOrderStatus(String orderNumber);

    //取消订单
    void cancel(String orderNumber);

    //添加一条订单信息

    void addOrderMaster(OrderMaster orderMaster);
    //保存订单信息中每一天商品的信息
    void addOrderDetail(OrderDetail orderDetail);
//!根据用户id获取订单数，可修改为待付款的订单数
    BigInteger countt(BigInteger userId);



}
