package com.tmall.controller;


import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.Cart;
import com.tmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /***
     * 根据用户ID获取其所有购物车信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseData<?>getCartById(@PathVariable("userId") BigInteger userId){
          List<Cart> carts =cartService.getCartById(userId);

          return ResponseDataUtils.buildSuccess("0", "购物车信息获取成功！",carts);
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
    public BigInteger getCartCount(@PathVariable("userId") BigInteger userId){
        return cartService.getCartCountById(userId);
    }

    /***
     * 用户添加商品到购物车
     * @param queryCart
     * @return
     */
    @PostMapping
    public ResponseData<?> addToCart(@RequestBody Cart queryCart){
        BigInteger userId = queryCart.getUserId();
        BigInteger productId = queryCart.getProductId();
        BigInteger count = queryCart.getCount();

        if (count==null)
            count=new BigInteger(String.valueOf(1));
        Cart cart=cartService.findCartItem(userId, productId);

        if (cart !=null)
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
    public ResponseData<?>deleteCart(@PathVariable("cartId") BigInteger cartId){
        cartService.deleteCart(cartId);
        return ResponseDataUtils.buildSuccess("0", "删除成功!");
    }

    /***
     * 清空购物车
     * @param userId
     * @return
     */
    @DeleteMapping("/all/{userId}")
    public ResponseData<?> deleteAll(@PathVariable BigInteger userId) {
         cartService.deleteAll(userId);

        return ResponseDataUtils.buildSuccess("0", "清空购物车成功！");
    }


}
