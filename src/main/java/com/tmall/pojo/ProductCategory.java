package com.tmall.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigInteger;

/***
 * 商品分类表
 */

@Data
@Accessors(chain = true)
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1890343468014366123L;
    //商品分类id
    private BigInteger categoryId;
    //商品分类名称
    private String categoryName;
}
