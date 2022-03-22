package com.tmall.service.impl;

import com.tmall.mapper.ProductMapper;
import com.tmall.pojo.Product;
import com.tmall.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 17:24
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> selectProductAll() {
        return productMapper.selectProductAll();
    }

    @Override
    public List<Product> findByPage(@Param("offset") int offset,
                                    @Param("pageSize") Integer pageSize,
                                    @Param("key") String key,
                                    @Param("ownerId") Integer ownerId) {
        return productMapper.findByPage(offset,pageSize,key,ownerId);
    }

    /**
     * 通过商家Id查询商品总数count
     * @param ownerId
     * @return
     */
    @Override
    public Integer countShopProduct(Integer ownerId) {
       Integer count = productMapper.countShopProduct(ownerId);
        return count;
    }

    /**
     * 修改商家商品信息
     * @param product
     */
    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    /**
     * 删除商家商品信息
     * @param productId
     */
    @Override
    public void deleteProductById(BigInteger productId) {
        productMapper.deleteProductById(productId);
    }
}
