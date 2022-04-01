package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.exception.OrderSendException;
import com.tmall.pojo.Order;
import com.tmall.pojo.OrderDetail;
import com.tmall.pojo.OrderMaster;
import com.tmall.pojo.PayInfo;
import com.tmall.service.OrderService;
import com.tmall.service.ProductService;
import com.tmall.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

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
        List<Order> orderMasters = orderService.findOrderById(buyerId);
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
    @PatchMapping("/send/{orderNumber}/{shopId}")
    public ResponseData<?> send(@PathVariable String orderNumber,
                                @PathVariable("shopId") BigInteger shopId){

        Integer orderStatus = orderService.getOrderStatus(orderNumber);
        if (orderStatus != 1) {
            throw new OrderSendException("订单状态异常,请联系商家处理");
        }else {
            orderService.sendByOrderNumber(orderNumber,shopId);
            return ResponseDataUtils.buildSuccess("0","订单发货成功请,提醒用户注意查收");
        }
    }

    // 取消订单
    @PatchMapping("/cancel/{orderNumber}/{shopId}")
    public ResponseData<?> cancel(@PathVariable String orderNumber,
                                  @PathVariable("shopId") BigInteger shopId){
        //获取订单状态
        Integer orderStatus = orderService.getOrderStatus(orderNumber);

        if (orderStatus == 1) {
            orderService.cancel(orderNumber,shopId);
            return ResponseDataUtils.buildSuccess("0","此为已付款订单已取消,请及时通知客户告知原因");
        }else {
            orderService.cancel(orderNumber,shopId);
            return ResponseDataUtils.buildSuccess("0","订单已取消,请及时通知客户");
        }
    }

    // 删除购物车对应的商品
    @DeleteMapping("/master")
    public ResponseData<?> addOrderMaster(@RequestBody OrderMaster orderMaster){
        System.out.println(orderMaster);
        orderService.deleteOrderMaster(orderMaster);
        return ResponseDataUtils.buildSuccess("0","购物车物品已删除");
    }

    //保存订单信息中每一件商品的信息
    @PostMapping("/detail")
    public ResponseData<?> addOrderDetail(@RequestBody OrderDetail orderDetail){
        orderService.addOrderDetail(orderDetail);
        return ResponseDataUtils.buildSuccess("0", "订单商品保存成功");
    }

    // 获取用户订单数
    @GetMapping("/count/{userId}")
    public ResponseData<?> getOrderCount(@PathVariable BigInteger userId) {
        BigInteger count =  orderService.getCount(userId);
        return ResponseDataUtils.buildSuccess("0", "获取订单数成功！", count);
    }

    // 用户自己取消订单
    @PatchMapping("/cancel1/{orderNumberAll}")
    public ResponseData<?> cancel1(@PathVariable String orderNumberAll) {
        orderService.cancel1(orderNumberAll);
        return ResponseDataUtils.buildSuccess("0", "取消成功!如有需要请再次订购.");
    }

    /**
     * 用户删除订单(实现软删除)
     */
    @DeleteMapping("/{orderNumberAll}")
    public ResponseData<?> deleteOrderFalse(@PathVariable String orderNumberAll) {
        orderService.deleteOrderFalse(orderNumberAll);
        return ResponseDataUtils.buildSuccess("0", "删除订单成功!");
    }

    /**
     * 付款
     */
    @PostMapping("/pay")
    public ResponseData<?> pay(@RequestBody PayInfo payInfo) {
        // 判断用户余额是否充足
        Double recharge = userService.getRecharge(payInfo.getUserId());
        if (recharge < payInfo.getPayMoney())
            return ResponseDataUtils.buildSuccess("1", "账户余额不足，请充值！");
        // 进行扣款
        userService.setRecharge(payInfo.getUserId(),payInfo.getPayMoney(),recharge);
        // 将订单设置为待收货状态(1标识待收货)
        orderService.updateOrderStatus(payInfo.getOrderNumber());
        // 商家店铺的收入增加
        userService.updateShopIncome(payInfo.getOrderNumber());
        // 增加对应商品的销量
        productService.addProductSold(payInfo.getOrderNumber());
        // 减少对应商品的库存
        productService.decreaseProductLeft(payInfo.getOrderNumber());
        return ResponseDataUtils.buildSuccess("0", "支付成功！");
    }
}
