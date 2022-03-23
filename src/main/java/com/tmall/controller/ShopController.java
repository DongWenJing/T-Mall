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
    @PostMapping("/user/shop/register")
    public ResponseData<?> register(@RequestBody Shop shop) {
        //检测下注册用户名是否重复
        Shop shopUsername = shopService.getUserByUsername(shop.getUsername());
        //用户名重复禁止注册
        if (shopUsername.equals(shop.getUsername())) {
            return ResponseDataUtils.buildSuccess("1", "该用户名已被使用！");
        }
        shopService.shopRegister(shop);
        return ResponseDataUtils.buildSuccess("0", "店铺注册成功,即将跳转登录");
    }

    /**
     * 根据userId获取商家信息
     */
    @GetMapping("/{userId}")
    public ResponseData<?> getShopByUserId(@PathVariable BigInteger userId) {
        Shop shop = shopService.getShopByUserId(userId);
        return ResponseDataUtils.buildSuccess("0", "获取店铺信息成功！", shop);
    }

    /**
     * 根据shopId获取店铺信息
     */
    @GetMapping("/shop_id/{shopId}")
    public ResponseData<?> getShopByShopId(@PathVariable BigInteger shopId){
        Shop shop = shopService.getShopByShopId(shopId);
        return ResponseDataUtils.buildSuccess("0","获取店铺信息成功！", shop);
    }

}

