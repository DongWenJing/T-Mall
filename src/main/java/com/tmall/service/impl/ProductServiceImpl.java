package com.tmall.service.impl;

import com.tmall.mapper.ProductMapper;
import com.tmall.pojo.Product;
import com.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 17:24
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> selectProductAll() {
        return productMapper.selectProductAll();
    }
}
