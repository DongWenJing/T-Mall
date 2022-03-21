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
}
