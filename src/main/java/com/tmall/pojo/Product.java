package com.tmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author R.Yu
 * @date 2022/3/19 17:20
 */
@Data
public class Product implements Serializable {
    // 商品ID
    private BigInteger productId;
    // 商品名称
    private String productName;
    // 商品创建时间
    private Timestamp createTime;
    // 商品单价
    private Double productPrice;
    // 商品折扣
    private Double discount;
    // 剩余商品量
    private BigInteger left;
    // 售出量
    private BigInteger sold;
    // 商品评分
    private Double rate;
    // 商品图片地址
    private String imgSrc;
    // 商品描述
    private String productDesc;
    // 商品所属分类ID
    private int categoryId;
    // 商品所属店的id
    private BigInteger shopId;
    // 商品重量
    private String weight;
}
