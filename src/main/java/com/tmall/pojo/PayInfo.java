package com.tmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author R.Yu
 * @date 2022/4/1 14:13
 */
@Data
public class PayInfo implements Serializable {
    private static final long serialVersionUID = -2723393946709416860L;

    private String orderNumber;
    private Double payMoney;
    private BigInteger userId;

}
