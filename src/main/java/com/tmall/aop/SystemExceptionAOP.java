package com.tmall.aop;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author R.Yu
 * @date 2022/3/22 09:44
 */
@RestControllerAdvice
public class SystemExceptionAOP {

    @ExceptionHandler({IOException.class,RuntimeException.class})
    public Object fail(Exception e) {
        Map<String, Object> map = new HashMap<String,Object>(16);
        map.put("msg", e.getMessage());
        map.put("code", 1);
        map.put("success",false);
        return map;
    }
}
