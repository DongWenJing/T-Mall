package com.tmall.service;

import com.tmall.pojo.ProductCategory;

import java.util.List;

public interface AdminProductCategoryService {

    //动态获取商品分类信息
    List<ProductCategory> findCategoryList(Integer level);

    //修改商品状态
    void updateStatus(ProductCategory productCategory);

    //新增商品分类
    void addCategoryList(ProductCategory productCategory);

    //修改商品分类信息
    void updateCategoryList(ProductCategory productCategory);

    //删除商品分类信息
    void deleteCategoryList(ProductCategory productCategory);
}
