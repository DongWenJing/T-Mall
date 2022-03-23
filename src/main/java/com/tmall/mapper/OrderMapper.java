package com.tmall.mapper;

import com.tmall.pojo.OrderMaster;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/23 14:34
 */
public interface OrderMapper {
    // 获取某用户的全部订单信息
    List<OrderMaster> findOrderById(BigInteger buyerId);
}
