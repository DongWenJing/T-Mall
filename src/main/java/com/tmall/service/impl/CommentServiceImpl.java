package com.tmall.service.impl;

import com.tmall.mapper.CommentMapper;
import com.tmall.pojo.Comment;
import com.tmall.pojo.DetailsComment;
import com.tmall.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    // 根据指定商品ID获取评论信息
    @Override
    public List<DetailsComment> findCommentsById(BigInteger productId) {
        return commentMapper.findCommentsById(productId);
    }

    //查询某个商品所有的评论数
    @Override
    public BigInteger getCountById(BigInteger productId) {
        return commentMapper.getCountById(productId);
    }

    //添加评论
    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);

    }

    //评论删除
    @Override
    public void deleteComment(BigInteger commentId) {

        commentMapper.deleteComment(commentId);
    }

    // 判断是否已经评论过
    @Override
    public boolean checkComment(BigInteger productId, BigInteger userId) {
        Comment comment = commentMapper.checkComment(productId,userId);
        return comment != null;
    }
}
