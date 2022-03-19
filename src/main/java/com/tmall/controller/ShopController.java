package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.Shop;
import com.tmall.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:32
 */
@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 商家注册模块
     * 请求类型:post
     * 返回值类型:ResponseData
     * 请求参数:Shop对象
     */
    @PostMapping
    public ResponseData<?> register(@RequestBody Shop shop){
      shopService.addUser(shop);
        return ResponseDataUtils.buildSuccess("0","店铺注册成功,即将跳转登录",shop);
    }

}

