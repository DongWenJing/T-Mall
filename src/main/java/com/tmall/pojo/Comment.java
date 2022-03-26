package com.tmall.pojo;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;


@Data
public class Comment {

    //评论id
    private BigInteger commentId;
    //商品信息
    private BigInteger productId;
    //评论内容
    private String commentText;
    //评论时间
    private Timestamp commentTime;
    //用户id
    private BigInteger userId;
}
