package com.tmall.util;

import com.tmall.exception.IllegalPhoneException;
import com.tmall.exception.PhoneNotNullException;
import org.springframework.util.StringUtils;

/**
 * 检查用户的电话号码是否符合规范
 * @author R.Yu
 * @date 2022/3/25 14:17
 */
public class CheckPhone {
    public static void checkPhone(String tel) {
        if (!StringUtils.hasLength(tel)) {
            throw new PhoneNotNullException("手机号必填");
        }else if (!tel.matches("[1][0-9]{10}")) {
            throw new IllegalPhoneException("手机格式错误");
        }
    }
}
