package com.tmall.service.impl;

import com.tmall.mapper.LikeMapper;
import com.tmall.pojo.Like;
import com.tmall.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeMapper likeMapper;

    // 检验用户是否收藏了某个商品
    @Override
    public Like findById(BigInteger productId, BigInteger userId) {

        return likeMapper.findById(productId, userId);
    }
    // 用户添加收藏
    @Override
    public void save(BigInteger productId, BigInteger userId) {
        likeMapper.save(productId, userId);
    }
}
