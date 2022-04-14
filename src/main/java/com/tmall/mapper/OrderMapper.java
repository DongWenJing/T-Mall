package com.tmall.mapper;

import com.tmall.pojo.Order;
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
    List<Order> findOrderById(BigInteger buyerId);

    //分页查询商家的订单信息
    List<OrderMaster> findByPage(@Param("offset") int offset,
                                 @Param("pageSize") Integer pageSize,
                                 @Param("shopId") BigInteger shopId);
    //查询订单条数
    Integer getCountByShopId(BigInteger shopId);

    //修改订单状态
    void sendByOrderNumber(@Param("orderNumber") String orderNumber,
                           @Param("shopId") BigInteger shopId);

    //获取订单状态
    Integer getOrderStatus(String orderNumber);

    //取消订单
    void cancel(@Param("orderNumber") String orderNumber,
                @Param("shopId")BigInteger shopId);

    //删除一条订单信息
    void deleteOrderMaster(OrderMaster orderMaster);

    //保存订单信息中每一天商品的信息
    void addOrderDetail(OrderDetail orderDetail);

    //根据用户id获取订单数，可修改为待付款的订单数
    BigInteger countt(BigInteger userId);

    // 获取该订单下的所有订单
    String getOrderNumbers(String orderNumberAll);

    // 取消该订单
    void cancel1(String s);

    // 修改该订单的状态
    void updateStatus(@Param("orderNumberAll") String orderNumberAll,
                      @Param("status") Integer status);

    // 获取子订单的商家id
    Integer getOrderShopId(String oNumber);

    // 获取order表中总订单号的状态
    Integer getOrderAllStatus(String orderNumber);

    // 获取该用户在该店的订单号
    List<String> getOrderNumbersByUS(@Param("userId") BigInteger userId,
                                     @Param("shopId") BigInteger shopId);

    // 查询已购买的商品
    List<BigInteger> getProductIdByON(String orderNumber);

    // 获取每个订单的收入
    Double getOrderAmount(String orderNumber);

    // 更新子订单的状态
    void updateChildOrderStatus(@Param("orderNumber") String orderNumber,
                                @Param("status") int status);

    // 获取用户id
    BigInteger getOrderUserId(String orderNumber);

    // 获取用户订单金额
    Double getOrderMoney(String orderNumber);

    // 获取订单总金额
    Double getOrderAllMoney(String orderNumberAll);

    // 获取用户id
    BigInteger getOrderUserIdByONA(String orderNumberAll);

    Order getOrderByONA(String orderNumber);

    void updateOrderMoney(@Param("price") double price,
                          @Param("orderNumberAll") String orderNumberAll);
}
