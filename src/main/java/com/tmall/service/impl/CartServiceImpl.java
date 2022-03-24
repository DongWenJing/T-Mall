package com.tmall.service.impl;

import com.tmall.mapper.CartMapper;
import com.tmall.pojo.Cart;
import com.tmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    //根据用户ID获取其所有购物车信息
    @Override
    @Transactional
    public List<Cart> getCartById(BigInteger userId) {
        return cartMapper.getCartById(userId);
    }

    // 修改某一个购物车商品的数量
    @Override
    public void setCartCount(BigInteger cartId, BigInteger count) {
        cartMapper.setCartCount(cartId,count);
    }

    // 获取某个用户的购物车信息的数量
    @Override
    @Transactional
    public BigInteger getCartCountById(BigInteger userId) {
        return cartMapper.getCartCountById(userId);
    }

    // 检验用户是否收藏了某一件商品
    @Override
    @Transactional
    public Cart findCartItem(BigInteger userId, BigInteger productId) {
        return cartMapper.findCartItem(userId, productId);
    }

    // 用户添加商品到购物车
    @Override
    public void addToCart(BigInteger userId, BigInteger productId, BigInteger count) {
        cartMapper.addToCart(userId,productId,count);

    }

    // 删除某用户的某一条购物车商品信息
    @Override
    public void deleteCart(BigInteger cartId) {
        cartMapper.deleteCart(cartId);
    }

    // 清空购物车
    @Override
    public void deleteAll(BigInteger userId) {
        cartMapper.deleteAll(userId);
    }


}
