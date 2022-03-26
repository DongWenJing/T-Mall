package com.tmall.service.impl;

import com.tmall.mapper.OrderMapper;
import com.tmall.pojo.OrderMaster;
import com.tmall.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/23 14:46
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderMaster> findOrderById(BigInteger buyerId) {
        return orderMapper.findOrderById(buyerId);
    }

    //分页查询店铺订单
    @Override
    public List<OrderMaster> findByPage(@Param("offset") int offset,
                                        @Param("pageSize") Integer pageSize,
                                        @Param("shopId") BigInteger shopId) {
        List<OrderMaster> findByPage= orderMapper.findByPage(offset,pageSize,shopId);
        return findByPage;
    }
    //查询订单条数
    @Override
    public Integer getCountByShopId(BigInteger shopId) {
        return orderMapper.getCountByShopId(shopId);
    }

    //修改订单状态
    @Override
    public void sendByOrderNumber(String orderNumber) {
        orderMapper.sendByOrderNumber(orderNumber);
    }

    //获取订单的状态
    @Override
    public Integer getOrderStatus(String orderNumber) {
        return orderMapper.getOrderStatus(orderNumber);
    }

    //取消订单
    @Override
    public void cancel(String orderNumber) {
        orderMapper.cancel(orderNumber);
    }
}
