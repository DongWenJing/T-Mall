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

    List<OrderDetail> findOrderProduct(String orderNumber);

    List<OrderDetail> findOrderDetail(@Param("orderNumber") String orderNumber,
                                      @Param("shopId") BigInteger shopId);

    List<OrderDetail> findOrderDetailByProductId(@Param("orderNumber") String orderNumber,
                                      @Param("productId") BigInteger productId);

    // 查询总订单号
    String findOrderNumberAll(String orderNumber);

    List<Product> search(String key);

    // 找到该商品对应的商家id
    Integer getShopIdByproductId(BigInteger productId);

    // 主要获取商品id和数量
    List<OrderDetail> getOderDetailByOrderNumber(String orderNumber);

    // 增加对应的销量
    void addSold(@Param("productId") BigInteger productId,
                 @Param("count") BigInteger count);

    // 减少对应商品的库存
    void decreaseProductLeft(@Param("productId") BigInteger productId,
                             @Param("count") BigInteger count);

    // 增加对应商品的库存
    void increaseProductLeft(@Param("productId") BigInteger productId,
                             @Param("count") BigInteger count);

    // 获取商品剩余数量
    BigInteger getProductLeft(BigInteger productId);

    // 查询对应订单的商品数量
    BigInteger getProductCountByOP(@Param("orderNumber") String orderNumber,
                                   @Param("productId") BigInteger productId);

    // 获取商品名字
    String getProductName(BigInteger productId);
}
