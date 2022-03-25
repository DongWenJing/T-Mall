package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.OrderMaster;
import com.tmall.service.OrderService;
import com.tmall.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Page page;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 获取用户所有的订单
     * @param buyerId
     * @return
     */
    @GetMapping("/{buyerId}")
    public ResponseData<?> findOrderById(@PathVariable BigInteger buyerId){
        List<OrderMaster> orderMasters = orderService.findOrderById(buyerId);

        return ResponseDataUtils.buildSuccess("0", "获取订单数成功！", orderMasters);
    }

    /**
     * 分页查询本店所有订单
     * @param pageNum
     * @param pageSize
     * @param shopId
     * @return
     */
    @GetMapping("/shop")
    public ResponseData<?> findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam BigInteger shopId){
        int offset = (pageNum - 1) * pageSize;
        System.out.println("shopId = " + shopId);
        List<OrderMaster> userData = orderService.findByPage(offset,pageSize,shopId);
        page.setData(userData);
       Integer total = orderService.getCountByShopId(shopId);
        page.setTotal(total);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return ResponseDataUtils.buildSuccess("0","加载信息完成",page);
    }
}
