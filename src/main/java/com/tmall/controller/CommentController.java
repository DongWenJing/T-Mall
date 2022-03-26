package com.tmall.controller;


import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.Comment;
import com.tmall.pojo.DetailsComment;
import com.tmall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /***
     *根据指定商品ID获取评论信息
     * @param productId
     * @return
     *                    该功能目前未测出!!!
     */
    @GetMapping("/{productId}")
    public List<DetailsComment>findCommentsById(@PathVariable("productId")
                                                        BigInteger productId){

        return commentService.findCommentsById(productId);


    }

    /***
     * 查询某个商品所有的评论数
     * @param productId
     * @return
     */
    @GetMapping("/count/{productId}")
    public BigInteger getCountById(@PathVariable("productId")
                                               BigInteger productId){
     return commentService.getCountById(productId);

    }

    /***
     * 添加评论
     * @param comment
     * @return
     */
    @PostMapping
    public ResponseData<?>insertComment(@RequestBody Comment comment){
        commentService.insertComment(comment);

        return ResponseDataUtils.buildSuccess("0", "评论成功!");

    }

    /***
     * 评论删除
     * @param commentId
     * @return
     */
    @DeleteMapping("/{commentId}")
    public ResponseData<?>deleteComment(@PathVariable BigInteger commentId){

        commentService.deleteComment(commentId);

        return ResponseDataUtils.buildSuccess("0", "删除评论成功");

    }
}
