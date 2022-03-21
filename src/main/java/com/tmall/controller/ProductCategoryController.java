package com.tmall.controller;


import com.tmall.pojo.Product;
import com.tmall.pojo.ProductCategory;
import com.tmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RequestMapping("/category")
@RestController
@CrossOrigin
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    //查询所有商品分类数据
    @GetMapping("/all")
    public List<ProductCategory> findAll(){
        return productCategoryService.findAll();
    }

    // 根据 category_id 查询此类所有商品
    @GetMapping("/categoryId")
    public List<Product> selectById(@PathVariable("categoryId") BigInteger categoryId){

        return productCategoryService.findById(categoryId);
    }

}
