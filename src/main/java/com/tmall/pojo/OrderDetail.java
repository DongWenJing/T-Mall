package com.tmall.pojo;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author R.Yu
 * @date 2022/3/23 14:11
 */
@Data
public class OrderDetail {
    private BigInteger detailId;
    // 订单编号
    private String orderNumber;
    // 商品id
    private BigInteger productId;
    // 商品图片地址
    private String imgSrc;
    // 商品名字
    private String productName;
    // 商品价格
    private Double productPrice;
    // 商品数量
    private BigInteger count;
    // 某一件订单中一类商品的总额
    private Double orderProductPrice;
}
