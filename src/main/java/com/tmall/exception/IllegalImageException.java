package com.tmall.exception;

import org.springframework.stereotype.Component;

/**
 * @author R.Yu
 * @date 2022/3/22 11:22
 */

public class IllegalImageException extends RuntimeException{
    public IllegalImageException(){}

    public IllegalImageException(String msg){
        super(msg);
    }
}
