package com.tmall.pojo;

import lombok.Data;
import java.math.BigDecimal;

/**
 *  发起支付时的参数
 *
 *   @Author : DongWJ
 *   @Date : 2022/4/1 23:46
 *   业务对象
 *
 * */
@Data
public class PaymentBo {
    //省略其他的业务参数，如商品id、购买数量等

    //商品名称
    private String subject;

    //总金额
    private Double total;
}