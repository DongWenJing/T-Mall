package com.tmall.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/28 23:20
 */
@Data
@Accessors(chain = true)
public class Order implements Serializable {
    private static final long serialVersionUID = -4380783058415031239L;

    private BigInteger allId;
    private String orderNumberAll;
    private String orderNumber;
    private double money;
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> orderDetails;
    private List<OrderMaster> orderMasters;
    private Integer orderStatus;
    private BigInteger buyerId;
}
