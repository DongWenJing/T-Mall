package com.tmall.exception;

/**
 * @Author : DongWJ
 * @Date : 2022/3/26 10:41
 */
public class OrderSendException extends RuntimeException{
    public OrderSendException() {
    }

    public OrderSendException(String message) {
        super(message);
    }
}
