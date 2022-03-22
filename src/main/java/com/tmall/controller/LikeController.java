package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.Like;
import com.tmall.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    /***
     * 用户添加收藏
     * @param queryLike
     * @return
     */
    @PostMapping
    public ResponseData<?> addLikeItem(@RequestBody Like queryLike) {
        BigInteger productId = queryLike.getProductId();
        BigInteger userId = queryLike.getUserId();
        Like like = likeService.findById(productId, userId);
        if (like != null) {
            return ResponseDataUtils.buildSuccess("1", "您已经收藏该商品！");
        }
        likeService.save(productId, userId);
        return ResponseDataUtils.buildSuccess("0", "收藏商品成功！");
    }


    }
