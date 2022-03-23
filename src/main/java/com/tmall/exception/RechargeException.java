package com.tmall.exception;

/**
 * @Author : DongWJ
 * @Date : 2022/3/23 23:50
 */
public class RechargeException extends RuntimeException{
    public RechargeException() {
    }

    public RechargeException(String message) {
        super(message);
    }
}
