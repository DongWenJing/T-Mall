package com.tmall.service;

import com.tmall.pojo.Cart;

import java.math.BigInteger;
import java.util.List;

public interface CartService {
    List<Cart> getCartById(BigInteger userId);

    void setCartCount(BigInteger cartId, BigInteger count);

    BigInteger getCartCountById(BigInteger userId);

    Cart findCartItem(BigInteger userId, BigInteger productId);

    void addToCart(BigInteger userId, BigInteger productId, BigInteger count);

    void deleteCart(BigInteger cartId);

    void deleteAll(BigInteger userId);

}

