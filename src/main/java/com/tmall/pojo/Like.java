package com.tmall.pojo;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
//用户收藏
@Data
public class Like {
    private BigInteger likeId;
    private String productName;
    private String productPrice;
    private BigInteger productId;
    private BigInteger userId;
    private Timestamp likeTime;
    private double discount;
    private String imgSrc;
}
