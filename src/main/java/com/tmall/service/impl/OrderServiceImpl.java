package com.tmall.service.impl;


import cn.hutool.core.date.DateTime;
import com.tmall.mapper.CartMapper;
import com.tmall.mapper.OrderMapper;
import com.tmall.mapper.ProductMapper;
import com.tmall.mapper.UserMapper;
import com.tmall.pojo.Order;
import com.tmall.pojo.OrderDetail;
import com.tmall.pojo.OrderMaster;
import com.tmall.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/23 14:46
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @Override
    public List<Order> findOrderById(BigInteger buyerId) {
        return orderMapper.findOrderById(buyerId);
    }

    //分页查询店铺订单
    @Override
    public List<OrderMaster> findByPage(@Param("offset") int offset,
                                        @Param("pageSize") Integer pageSize,
                                        @Param("shopId") BigInteger shopId) {
        List<OrderMaster> findByPage = orderMapper.findByPage(offset, pageSize, shopId);
        return findByPage;
    }

    //查询订单条数
    @Override
    public Integer getCountByShopId(BigInteger shopId) {
        return orderMapper.getCountByShopId(shopId);
    }

    //修改订单状态(商家发货)
    @Override
    public void sendByOrderNumber(String orderNumber, BigInteger shopId) {
        // 修改子订单号状态
        orderMapper.sendByOrderNumber(orderNumber, shopId);
        // 拿到总订单号下的子订单
        String orderNumberAll = productMapper.findOrderNumberAll(orderNumber);
        String[] oN = orderMapper.getOrderNumbers(orderNumberAll).split(",");
        boolean flag = true;
        for (String s : oN) {
            // 需要判断子订单的状态是否都为3
            if (orderMapper.getOrderStatus(s) != 3) {
                flag = false;
                break;
            }
        }
        if (flag) orderMapper.updateStatus(orderNumberAll, 3);
    }

    //获取订单的状态
    @Override
    public Integer getOrderStatus(String orderNumber) {
        return orderMapper.getOrderStatus(orderNumber);
    }

    //取消订单
    @Override
    public void cancel(String orderNumber, BigInteger shopId) {
        // 将该订单的状态改为2(已取消的状态)
        orderMapper.cancel(orderNumber, shopId);
        String orderNumberAll = productMapper.findOrderNumberAll(orderNumber);
        // 获取用户id
        BigInteger userId = orderMapper.getOrderUserId(orderNumber);
        // 获取用户订单金额
        Double money = orderMapper.getOrderMoney(orderNumber);
        // 获取用户现在的金额
        Double balance = userMapper.getRecharge(userId);
        money = money + balance;
        userMapper.addRecharge(userId, money);

        String[] oN = orderMapper.getOrderNumbers(orderNumberAll).split(",");
        if (oN.length == 1) {
            orderMapper.updateStatus(orderNumberAll, 2);
        }
        // 若不是,则无需处理

    }

    // 清空购物车
    @Override
    public void deleteOrderMaster(OrderMaster orderMaster) {
        orderMapper.deleteOrderMaster(orderMaster);
    }

    //保存订单信息中每一件商品的信息
    @Override
    @Transactional
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetail.setUpdateTime(new Date());
        orderMapper.addOrderDetail(orderDetail);
        System.out.println("orderDetail = " + orderDetail);
    }

    //获取用户订单数
    @Override
    public BigInteger getCount(BigInteger userId) {
        return orderMapper.countt(userId);
    }

    // 用户自己取消订单
    @Transactional
    @Override
    public void cancel1(String orderNumberAll) {
        String orderNumbers = orderMapper.getOrderNumbers(orderNumberAll);
        String[] oN = orderNumbers.split(",");
        for (String s : oN) {
            orderMapper.cancel1(s);
        }
        Integer orderAllStatus = orderMapper.getOrderAllStatus(orderNumberAll);
        if (orderAllStatus == 0) {
            orderMapper.updateStatus(orderNumberAll, 2);
            return;
        }
        orderMapper.updateStatus(orderNumberAll, 2);
        // 退款至账户
        Double orderAllMoney = orderMapper.getOrderAllMoney(orderNumberAll);
        BigInteger userId = orderMapper.getOrderUserIdByONA(orderNumberAll);
        Double money = userMapper.getRecharge(userId);
        money += orderAllMoney;
        userMapper.addRecharge(userId, money);
        // 库存增加
        List<OrderDetail> orderDetailList = productMapper.getOderDetailByOrderNumber(orderNumberAll);
        orderDetailList.forEach(s -> productMapper.increaseProductLeft(s.getProductId(),s.getCount()));
    }

    // 用户删除订单(实现软删除)
    @Override
    @Transactional
    public void deleteOrderFalse(String orderNumberAll) {
        orderMapper.updateStatus(orderNumberAll, 4);
        // 商家对应的订单应该调为已取消
        String[] oN = orderMapper.getOrderNumbers(orderNumberAll).split(",");
        for (String s : oN) {
            orderMapper.cancel1(s);
        }
    }

    // 将订单设置为待收货状态(1标识待收货)
    @Override
    @Transactional
    public void updateOrderStatus(String orderNumberAll) {
        orderMapper.updateStatus(orderNumberAll, 1);
        // 获取子订单
        String[] orderNumberList = orderMapper.getOrderNumbers(orderNumberAll).split(",");
        for (String orderNumber : orderNumberList) {
            orderMapper.updateChildOrderStatus(orderNumber, 1);
        }
    }

}
