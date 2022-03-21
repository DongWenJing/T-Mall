package com.tmall.controller;

import com.tmall.pojo.Product;
import com.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 17:27
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 用于所有商品的展示
     * @return
     */
    @GetMapping("/all")
    public List<Product> doSelectProductAll() {
        return productService.selectProductAll();
    }
}
