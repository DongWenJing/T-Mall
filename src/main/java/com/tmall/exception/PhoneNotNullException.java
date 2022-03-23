package com.tmall.exception;

/**
 * @author R.Yu
 * @date 2022/3/23 08:45
 */
public class PhoneNotNullException extends RuntimeException {
    public PhoneNotNullException() {
    }

    public PhoneNotNullException(String message) {
        super(message);
    }
}
