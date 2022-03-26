package com.tmall.mapper;

import com.tmall.pojo.Comment;
import com.tmall.pojo.DetailsComment;
import org.apache.ibatis.annotations.Select;

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
}
