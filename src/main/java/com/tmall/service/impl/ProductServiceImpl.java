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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        return productMapper.findByPage(offset, pageSize, key, ownerId);
    }

    /**
     * 通过商家Id查询商品总数count
     *
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
     *
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
     *
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
     *
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
    public List<OrderDetail> findOrderDetail(String orderNumber, BigInteger shopId) {
        String orderNumberAll = productMapper.findOrderNumberAll(orderNumber);
        return productMapper.findOrderDetail(orderNumberAll, shopId);
    }

    @Override
    public List<OrderDetail> findOrderDetailByProductId(String orderNumber, BigInteger productId) {
        return productMapper.findOrderDetailByProductId(orderNumber, productId);
    }

    // 用户.我的订单的详情展示
    @Override
    public List<OrderDetail> findOrderProduct(String orderNumber) {
        // 判断该总订单的状态是否为已取消
        Integer statusAll = orderMapper.getOrderAllStatus(orderNumber);
        if (statusAll == 2) {
            return productMapper.findOrderProduct(orderNumber);
        }
        // 创建一个满足条件的orderDetail列表
        List<OrderDetail> res = new ArrayList<>();
        // 获取子订单
        String orderNumbers = orderMapper.getOrderNumbers(orderNumber);
        String[] oN = orderNumbers.split(",");
        // 获取每个商品的详情
        List<OrderDetail> orderDetails = productMapper.findOrderProduct(orderNumber);
        // 获取每个订单明细
        for (OrderDetail orderDetail : orderDetails) {
            // 获取每个商品的id
            BigInteger productId = orderDetail.getProductId();
            // 找到该商品对应的商家id
            Integer shopId = productMapper.getShopIdByproductId(productId);
            orderDetail.setShopId(shopId);
            // 遍历子订单进行查询
            for (String oNumber : oN) {
                // 获取子订单的状态
                Integer status = orderMapper.getOrderStatus(oNumber);
                // 判断查询出来的商家id是否相同
                if (orderDetail.getShopId().equals(orderMapper.getOrderShopId(oNumber)))
                    orderDetail.setOrderStatus(status);
            }
            // 将不是取消状态的商品添加进去
            if (orderDetail.getOrderStatus() != 2)
                res.add(orderDetail);
        }
        return res;
    }

    //首页搜索功能
    @Override
    public List<Product> search(String key) {
        return productMapper.search(key);
    }

    //  增加对应商品的销量
    @Transactional
    @Override
    public void addProductSold(String orderNumber) {
        // 获取商品id
        List<OrderDetail> orderDetailList = productMapper.getOderDetailByOrderNumber(orderNumber);
        for (OrderDetail orderDetail : orderDetailList) {
            // 增加对应的销量
            productMapper.addSold(orderDetail.getProductId(), orderDetail.getCount());
        }
    }

    // 减少对应商品的库存
    @Transactional
    @Override
    public void decreaseProductLeft(String orderNumber) {
        List<OrderDetail> orderDetails = productMapper.getOderDetailByOrderNumber(orderNumber);
        for (OrderDetail orderDetail : orderDetails) {
            productMapper.decreaseProductLeft(orderDetail.getProductId(),orderDetail.getCount());
        }
    }
}
