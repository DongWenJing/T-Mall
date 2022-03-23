package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.OrderMaster;
import com.tmall.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/23 14:25
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{buyerId}")
    public ResponseData<?> findOrderById(@PathVariable BigInteger buyerId){
        List<OrderMaster> orderMasters = orderService.findOrderById(buyerId);;
        return ResponseDataUtils.buildSuccess("0", "获取订单数成功！", orderMasters);
    }
}
