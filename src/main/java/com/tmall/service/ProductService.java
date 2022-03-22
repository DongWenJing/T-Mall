package com.tmall.service;

import com.tmall.pojo.Product;

import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 17:23
 */
public interface ProductService {
    /**
     * 用于展现所有商品
     * @return
     */
    List<Product> selectProductAll();

    List<Product> findByPage(int offset, Integer pageSize, String key, Integer ownerId);

    Integer countShopProduct(Integer ownerId);

    void updateProduct(Product product);
}
