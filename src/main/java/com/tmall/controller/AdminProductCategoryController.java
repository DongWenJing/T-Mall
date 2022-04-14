package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.ProductCategory;
import com.tmall.service.AdminProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class AdminProductCategoryController {

    @Autowired
    private AdminProductCategoryService adminProductCategoryService;

    //动态获取商品分类信息
    @GetMapping("/findCategoryList/{level}")
    public ResponseData<ProductCategory> findProductCategoryList(@PathVariable Integer level){
        List<ProductCategory> categoryList = adminProductCategoryService.findCategoryList(level);
        return ResponseDataUtils.buildSuccess(categoryList);
    }

    //更改商品状态
    @PutMapping("/status/{categoryId}/{status}")
    public ResponseData<ProductCategory> updateStatus(ProductCategory productCategory){
        adminProductCategoryService.updateStatus(productCategory);
        return ResponseDataUtils.buildSuccess();
    }

    //新增商品分类
    @PostMapping("/saveCategory")
    public ResponseData<ProductCategory> addCategoryList(@RequestBody ProductCategory productCategory){
        adminProductCategoryService.addCategoryList(productCategory);
        return ResponseDataUtils.buildSuccess();
    }

    //修改商品分类信息
    @PutMapping("/updateCategory")
    public ResponseData<ProductCategory> updateCategoryList(@RequestBody ProductCategory productCategory){
        adminProductCategoryService.updateCategoryList(productCategory);
        return ResponseDataUtils.buildSuccess();
    }

    //删除商品分类信息
    @DeleteMapping("/delete")
    public ResponseData<ProductCategory> deleteCategoryList(ProductCategory productCategory){
        adminProductCategoryService.deleteCategoryList(productCategory);
        return ResponseDataUtils.buildSuccess();
    }
}
