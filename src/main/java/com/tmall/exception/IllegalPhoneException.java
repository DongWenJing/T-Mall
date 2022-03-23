package com.tmall.exception;

/**
 * @author R.Yu
 * @date 2022/3/23 09:02
 */
public class IllegalPhoneException extends RuntimeException {
    public IllegalPhoneException() {
    }

    public IllegalPhoneException(String msg) {
        super(msg);
    }
}
