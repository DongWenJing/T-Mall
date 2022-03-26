package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.exception.OrderSendException;
import com.tmall.pojo.OrderDetail;
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

    // 商家发货
    @PatchMapping("/send/{orderNumber}")
    public ResponseData<?> send(@PathVariable String orderNumber){

        Integer orderStatus = orderService.getOrderStatus(orderNumber);
        if (orderStatus != 1) {
            throw new OrderSendException("订单状态异常,请联系商家处理");
        }else {
            orderService.sendByOrderNumber(orderNumber);
            return ResponseDataUtils.buildSuccess("0","订单发货成功请,提醒用户注意查收");
        }
    }

    // 取消订单
    @PatchMapping("/cancel/{orderNumber}")
    public ResponseData<?> cancel(@PathVariable String orderNumber){
        //获取订单状态
        Integer orderStatus = orderService.getOrderStatus(orderNumber);

        if (orderStatus == 1) {
            orderService.cancel(orderNumber);
            return ResponseDataUtils.buildSuccess("0","此为已付款订单已取消,请及时通知客户告知原因");
        }else {
            orderService.cancel(orderNumber);
            return ResponseDataUtils.buildSuccess("0","订单已取消,请及时通知客户");
        }
    }

    //添加一条订单
    @PostMapping("/master")
    public ResponseData<?> addOrderMaster(@RequestBody OrderMaster orderMaster){
        orderService.addOrderMaster(orderMaster);
        return ResponseDataUtils.buildSuccess("0","订单提交成功");
    }

    //保存订单信息中每一天商品的信息

    @PostMapping("/detail")
    public ResponseData<?> addOrderDetail(@RequestBody OrderDetail orderDetail){
        orderService.addOrderDetail(orderDetail);
        return ResponseDataUtils.buildSuccess("0", "订单商品保存成功");
    }

    // 获取用户订单数
    @GetMapping("/count/{userId}")
    public  ResponseData<?> getOrderCount(@PathVariable BigInteger userId) {
        BigInteger count =  orderService.getCount(userId);
        return ResponseDataUtils.buildSuccess("0", "获取订单数成功！", count);
    }

    // @GetMapping
}
