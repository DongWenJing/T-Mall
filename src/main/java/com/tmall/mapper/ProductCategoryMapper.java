package com.tmall.mapper;


import com.tmall.pojo.Product;
import com.tmall.pojo.ProductCategory;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public interface ProductCategoryMapper {


    List<ProductCategory> findAll();

    List<Product> findById(BigInteger categoryId);

    //添加分类
    void addCategory(ProductCategory productCategory);

    //编辑分类
    void setCategory(ProductCategory productCategory);

    // 删除分类
    void removeCategory(BigInteger categoryId);
}
