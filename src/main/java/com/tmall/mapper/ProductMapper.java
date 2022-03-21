package com.tmall.mapper;

import com.tmall.pojo.Product;

import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 17:22
 */
public interface ProductMapper {
    List<Product> selectProductAll();
}
