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

    /***
     * 获取用户收藏商品的总数
     * @param userId
     * @return
     */
    @GetMapping("/count/{userId}")
    public ResponseData<?> countById(@PathVariable("userId") BigInteger userId) {
        BigInteger counts = likeService.counts(userId);
        return ResponseDataUtils.buildSuccess("0", "数据获取成功！", counts);
    }

    /***
     * 获取用户的收藏商品
      * @param userId
     * @return
     */

    @GetMapping("/{userId}")
    public ResponseData<?> getLikeItemsById(@PathVariable("userId") BigInteger userId) {

        List<Like> likes = likeService.findAll(userId);

        return ResponseDataUtils.buildSuccess("0", "数据获取成功！", likes);
    }

    /***
     * 用户删除收藏商品
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteById(@PathVariable("userId") BigInteger userId) {
        likeService.delete(userId);

        return ResponseDataUtils.buildSuccess("0", "收藏删除成功！");
    }


    }
