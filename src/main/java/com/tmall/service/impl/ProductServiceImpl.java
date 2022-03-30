package com.tmall.service.impl;

import com.tmall.mapper.OrderMapper;
import com.tmall.exception.RechargeException;
import com.tmall.mapper.ProductMapper;
import com.tmall.pojo.OrderDetail;
import com.tmall.pojo.Product;
import com.tmall.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private OrderMapper orderMapper;

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

    @Override
    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }



    /**
     * 删除商家商品信息
     * @param productId
     */
    @Override
    public void deleteProductById(BigInteger productId) {
        productMapper.deleteProductById(productId);
    }

    @Override
    public List<Product> findByShopId(BigInteger shopId) {
        return productMapper.findShopById(shopId);
    }

    /**
     * 展现每个商品的信息
     * @param productId
     * @return
     */
    @Override
    public Product showProductInfo(BigInteger productId) {
        return productMapper.showProductInfo(productId);
    }

    @Override
    public List<Product> getHotProduct() {
        return productMapper.getHotProduct();
    }

    @Override
    public List<Product> getNewProduct() {
        return productMapper.getNewProduct();
    }

    @Override
    public List<OrderDetail> findOrderDetail( String orderNumber,BigInteger shopId) {
        String orderNumberAll = productMapper.findOrderNumberAll(orderNumber);
        return productMapper.findOrderDetail(orderNumberAll, shopId);
    }

    @Override
    public List<OrderDetail> findOrderDetailByProductId(String orderNumber, BigInteger productId) {
        return productMapper.findOrderDetailByProductId(orderNumber, productId);
    }

    @Override
    public List<OrderDetail> findOrderProduct(String orderNumber) {
       /* String orderNumbers = orderMapper.getOrderNumbers(orderNumber);
        String[] oN = orderNumbers.split(",");
        for (String s : oN) {
            List<OrderDetail> orderProduct = productMapper.findOrderProduct(s);
            orderProduct
        }*/
        return productMapper.findOrderProduct(orderNumber);
    }

    //首页搜索功能
    @Override
    public List<Product> search(String key) {
         List<Product> result = productMapper.search(key);
         return result;
    }
}
