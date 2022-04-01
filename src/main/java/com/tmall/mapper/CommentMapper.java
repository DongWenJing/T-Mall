package com.tmall.mapper;

import com.tmall.pojo.Comment;
import com.tmall.pojo.DetailsComment;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface CommentMapper {

    // 根据指定商品ID获取评论信息
    List<DetailsComment> findCommentsById(BigInteger productId);

    // 查询某个商品所有的评论数
    BigInteger getCountById(BigInteger productId);

    //添加评论
    void insertComment(Comment comment);

    //删除评论
    void deleteComment(BigInteger commentId);

    // 判断是否已经评论过
    Comment checkComment(@Param("productId") BigInteger productId,
                         @Param("userId") BigInteger userId);
}
