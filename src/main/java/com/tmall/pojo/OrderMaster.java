package com.tmall.pojo;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author R.Yu
 * @date 2022/3/23 14:16
 */
@Data
public class OrderMaster {

    private Integer orderId;
    // 订单编号,前端生成
    private String orderNumber;
    // 客户ID
    private Integer buyerId;
    // 此次订单总金额
    private Double orderAmount;
    // 下单时间
    // private Timestamp createTime;
    private Date createTime;
    // 红 蓝 绿 灰
    // !订单状态：0表示待付款，1表示待收货，2表示已取消，3表示已完成
    private Integer orderStatus;

    private BigInteger shopId;
}
