package com.tmall.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/***
 * 商品分类表
 */

@Data
@Accessors(chain = true)
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1890343468014366123L;
    //商品分类id
    private Integer categoryId;
    //商品分类名称
    private String categoryName;
    //定义父级菜单 开启驼峰规则映射
    private Integer parentId;
    //分类状态 0 停用 1 正常
    private Boolean status;
    //商品分类等级 1 2 3
    private Integer level;
    //用于封装数据
    private List<ProductCategory> children;

}
