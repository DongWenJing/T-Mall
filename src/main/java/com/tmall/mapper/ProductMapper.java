package com.tmall.mapper;

import com.tmall.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    //@Select("SELECT count(*) FROM product WHERE shop_id=(SELECT shop_id FROM shop WHERE owner_id=#{ownerId})")
    Integer countShopProduct(Integer ownerId);
}
