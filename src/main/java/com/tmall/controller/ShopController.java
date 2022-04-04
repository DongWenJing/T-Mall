package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.Shop;
import com.tmall.service.ShopService;
import com.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private UserService userService;


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
    public ResponseData<?> getShopByShopId(@PathVariable BigInteger shopId) {
        Shop shop = shopService.getShopByShopId(shopId);
        return ResponseDataUtils.buildSuccess("0", "获取店铺信息成功！", shop);
    }

    /**
     * 注册商家
     */
    @Transactional
    @PostMapping
    public ResponseData<?> register(@RequestBody Shop shop) {
        // 先获取到用户id
        BigInteger userId = shopService.getUserId(shop.getUsername());
        // 设置为商家的所有者
        shop.setOwnerId(userId);
        // 进行完善商家注册
        shopService.shopRegister(shop);
        return ResponseDataUtils.buildSuccess("0", "商家注册成功!");
    }

    // 通过产品id获取商家id
    @GetMapping("/product_id/{productId}")
    public BigInteger getShopId(@PathVariable BigInteger productId) {
        return shopService.getShopIdByProductId(productId);
    }

    //查询店铺的销售额
    @GetMapping("/getsale/{shopId}")
    public ResponseData getSale(@PathVariable Double shopId) {
        Double sale = shopService.getSale(shopId);
        return ResponseDataUtils.buildSuccess("0", "", sale);
    }

    //查询店铺的销量
    @GetMapping("/getVolume/{shopId}")
    public ResponseData getVolume(@PathVariable Integer shopId) {
        Integer volume = shopService.getVolume(shopId);
        return ResponseDataUtils.buildSuccess("0", "", volume);
    }
}

