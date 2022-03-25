package com.tmall.service;


import com.tmall.pojo.Product;
import com.tmall.pojo.ProductCategory;

import java.math.BigInteger;
import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findAll();

    List<Product> findById(BigInteger categoryId);

    void addCategory(ProductCategory productCategory);

    void setCategory(ProductCategory productCategory);

    // 删除分类成功
    void removeCategory(BigInteger categoryId);
}
