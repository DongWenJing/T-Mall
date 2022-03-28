package com.tmall.mapper;

import com.tmall.pojo.OrderDetail;
import com.tmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 17:22
 */
public interface ProductMapper {
    List<Product> selectProductAll();

    //商家商品信息的分页查询
    List<Product> findByPage(@Param("offset") int offset,
                             @Param("pageSize") Integer pageSize,
                             @Param("key") String key,
                             @Param("ownerId") Integer ownerId);
    //查询商品总数
    Integer countShopProduct(Integer ownerId);

    //修改商家的商品信息
    void updateProduct(Product product);

    // 商家端的商品新增
    void insertProduct(Product product);
    //删除商家商品信息
    void deleteProductById(BigInteger productId);

    List<Product> findShopById(BigInteger shopId);

    // 展现每个商品的详情
    Product showProductInfo(BigInteger productId);

    // 获取最热产品
    List<Product> getHotProduct();

    List<Product> getNewProduct();

    List<OrderDetail> findOrderDetail(String orderNumber);

    List<Product> search(String key);
}
