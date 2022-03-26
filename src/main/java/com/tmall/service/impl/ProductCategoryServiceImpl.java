package com.tmall.service.impl;

import com.tmall.mapper.ProductCategoryMapper;
import com.tmall.pojo.Product;
import com.tmall.pojo.ProductCategory;
import com.tmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

    /**
     * 根据 category_id 查询此类所有商品
     * @param categoryId
     * @return
     */
    @Override
    public List<Product> findById(BigInteger categoryId) {
        return productCategoryMapper.findById(categoryId);
    }


    // 添加分类
    @Override
    public void addCategory(ProductCategory productCategory) {

        productCategoryMapper.addCategory(productCategory);
    }

    //编辑分类
    @Override
    public void setCategory(ProductCategory productCategory) {
        productCategoryMapper.setCategory(productCategory);
    }

    // 删除分类
    @Override
    public void removeCategory(BigInteger categoryId) {
        productCategoryMapper.removeCategory(categoryId);
    }

}
