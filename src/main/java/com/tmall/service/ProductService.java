package com.tmall.service;

import com.tmall.pojo.OrderDetail;
import com.tmall.pojo.Product;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 17:23
 */
public interface ProductService {
    /**
     * 用于展现所有商品
     * @return
     */
    List<Product> selectProductAll();

    /**
     * 分页显示商家的所有商品
     * @param offset
     * @param pageSize
     * @param key
     * @param ownerId
     * @return
     */
    List<Product> findByPage(int offset, Integer pageSize, String key, Integer ownerId);

    Integer countShopProduct(Integer ownerId);

    /**
     * 商家端的商品新增
     * @param product
     */
    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProductById(BigInteger productId);

    List<Product> findByShopId(BigInteger shopId);

    Product showProductInfo(BigInteger productId);

    List<Product> getHotProduct();

    List<Product> getNewProduct();

    List<OrderDetail> findOrderDetail(String orderNumber,BigInteger shopId);

    List<OrderDetail> findOrderDetailByProductId(String orderNumber,BigInteger productId);

    List<OrderDetail> findOrderProduct(String orderNumber);

    List<Product> search(String key);

    //  增加对应商品的销量
    void addProductSold(String orderNumber);

    // 减少对应商品的库存
    void decreaseProductLeft(String orderNumber);

}
