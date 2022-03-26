package com.tmall.service;

import com.tmall.pojo.Comment;
import com.tmall.pojo.DetailsComment;

import java.math.BigInteger;
import java.util.List;

public interface CommentService {
    List<DetailsComment> findCommentsById(BigInteger productId);

    BigInteger getCountById(BigInteger productId);

    void insertComment(Comment comment);

    void deleteComment(BigInteger commentId);

}
