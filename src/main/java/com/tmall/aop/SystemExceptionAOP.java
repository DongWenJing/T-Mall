package com.tmall.aop;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.exception.*;
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
        Map<String, Object> map = new HashMap<String,Object>(16);
    {
        map.put("code", 1);
        map.put("success",false);
    }

    @ExceptionHandler({IOException.class, PasswordException.class,
            IllegalImageException.class, PhoneNotNullException.class,
            RechargeException.class,RuntimeException.class})
    public Object fail(Exception e) {
        if (e instanceof IllegalImageException) {
            map.put("msg", e.getMessage());
            return map;
        }
        if (e instanceof PhoneNotNullException) {
            map.put("msg", e.getMessage());
            return map;
        }
        if (e instanceof IllegalPhoneException) {
            map.put("msg", e.getMessage());
            return map;
        }
        if (e instanceof PasswordException) {
            map.put("msg", e.getMessage());
            return map;
        }
        if (e instanceof RechargeException) {
            map.put("msg", e.getMessage());
            return map;
        }
        e.printStackTrace();
        return ResponseDataUtils.buildError("1", e.getMessage());
    }
}
