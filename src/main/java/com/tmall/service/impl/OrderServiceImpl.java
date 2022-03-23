package com.tmall.service.impl;

import com.tmall.mapper.OrderMapper;
import com.tmall.pojo.OrderMaster;
import com.tmall.service.OrderService;
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
}
