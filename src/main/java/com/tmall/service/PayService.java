package com.tmall.service;

import com.tmall.pojo.PaymentBo;
import com.tmall.pojo.User;

/**
 * @Author : DongWJ
 * @Date : 2022/4/2 16:05
 */
public interface PayService {


    String pay(User user) throws Exception;
}
