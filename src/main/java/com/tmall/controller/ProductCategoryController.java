package com.tmall.controller;


import com.tmall.pojo.ProductCategory;
import com.tmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
