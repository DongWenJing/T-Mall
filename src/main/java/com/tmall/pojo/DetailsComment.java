package com.tmall.pojo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class DetailsComment {
    //评论表属性

    //评论ID
    private BigInteger commentId;
    //评论内容
    private String commentText;
    //评论名字
    private String username;
    //user表格的头像地址
    private String avatar;
}
