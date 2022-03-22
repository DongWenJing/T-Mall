package com.tmall.controller;


import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
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
    @GetMapping("/{categoryId}")
    public List<Product> selectById(@PathVariable("categoryId") BigInteger categoryId){

        return productCategoryService.findById(categoryId);
    }

    //添加分类
    @PostMapping
    public ResponseData<?> addCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.addCategory(productCategory);
        return ResponseDataUtils.buildSuccess("0", "添加分类成功！");
    }

    //编辑分类
    @PutMapping
    public ResponseData<?> setCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.setCategory(productCategory);
        return ResponseDataUtils.buildSuccess("0", "修改分类成功！");
    }

}
