package com.tmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class Cart implements Serializable {
    private static final long serialVersionUID = 8621493151388049220L;
    private BigInteger cartId;
    private BigInteger productId;
    private Double discount;
    private BigInteger userId;
    private String imgSrc;
    private String productName;
    private Double productPrice;
    private Double weight;
    private BigInteger count;
}
