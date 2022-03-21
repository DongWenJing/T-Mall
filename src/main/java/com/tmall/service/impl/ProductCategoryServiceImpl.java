package com.tmall.service.impl;

import com.tmall.mapper.ProductCategoryMapper;
import com.tmall.pojo.ProductCategory;
import com.tmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    /***
     * 查询所有商品分类数据
     * @return
     */
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryMapper.findAll();
    }
}
