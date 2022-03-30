package com.tmall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成总订单号: 日期+7位随机数
 * @author R.Yu
 * @date 2022/3/28 23:23
 */
public class RandOrderNumber {
    public static String getRandOrderNumber() {
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String randNo = "";
        for (int i = 0; i < 7; i++) {
            randNo += (int)(Math.floor(Math.random() * 10));
        }
        return time + randNo;
    }
}
