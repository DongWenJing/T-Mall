package com.tmall.controller;


import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.Cart;
import com.tmall.pojo.Order;
import com.tmall.pojo.OrderMaster;
import com.tmall.service.CartService;
import com.tmall.service.ProductService;
import com.tmall.util.RandOrderNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    /***
     * 根据用户ID获取其所有购物车信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseData<?> getCartById(@PathVariable("userId") BigInteger userId) {
        List<Cart> carts = cartService.getCartById(userId);

        return ResponseDataUtils.buildSuccess("0", "购物车信息获取成功！", carts);
    }

    /***
     * 修改某一个购物车商品的数量
     * @param cartId
     * @param cart
     * @return
     */
    @PatchMapping("/{cartId}")
    public ResponseData<?> setCartCount(@PathVariable("cartId") BigInteger cartId,
                                        @RequestBody Cart cart) {
        cartService.setCartCount(cartId, cart.getCount());

        return ResponseDataUtils.buildSuccess("0", "购物车信息修改成功");
    }

    /***
     * 获取某个用户的购物车信息的数量
     * @param userId
     * @return
     */
    @GetMapping("/count/{userId}")
    public BigInteger getCartCount(@PathVariable("userId") BigInteger userId) {
        return cartService.getCartCountById(userId);
    }

    /***
     * TODO: 需要判断是否还有对应商品
     * 用户添加商品到购物车
     * @param queryCart
     * @return
     */
    @PostMapping
    public ResponseData<?> addToCart(@RequestBody Cart queryCart) {
        BigInteger userId = queryCart.getUserId();
        BigInteger productId = queryCart.getProductId();
        BigInteger count = queryCart.getCount();
        if (count == null)
            count = new BigInteger(String.valueOf(1));
        Cart cart = cartService.findCartItem(userId, productId);
        if (cart != null)
            return ResponseDataUtils.buildSuccess("1", "您已将该商品加入购物车！");
        cartService.addToCart(userId, productId, count);
        return ResponseDataUtils.buildSuccess("0", "成功加入到购物车！");

    }

    /***
     * 删除某用户的某一条购物车商品信息
     * @param cartId
     * @return
     */
    @DeleteMapping("/{cartId}")
    @Transactional
    public ResponseData<?> deleteCart(@PathVariable("cartId") BigInteger cartId) {
        cartService.deleteCart(cartId);
        return ResponseDataUtils.buildSuccess("0", "删除成功!");
    }

    /***
     * 将该方法转为写订单(order_master)
     * @param userId
     * @return
     */
    @GetMapping("/all/{userId}/{orderNumber}")
    public ResponseData<?> getCartProductIds(@PathVariable BigInteger userId,
                                             @PathVariable String orderNumber) {
        cartService.getCartProductIds(userId,orderNumber);
        return ResponseDataUtils.buildSuccess("0", "获取购物车中的产品id成功");
    }

    /**
     * 清空购物车
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseData<?> deleteCartByUserId(@PathVariable BigInteger userId) {
        cartService.deleteCartByUserId(userId);
        return ResponseDataUtils.buildSuccess("0", "清空购物车成功!");
    }

}
