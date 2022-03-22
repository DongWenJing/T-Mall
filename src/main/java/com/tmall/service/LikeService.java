package com.tmall.service;

import com.tmall.pojo.Like;

import java.math.BigInteger;
import java.util.List;

public interface LikeService {


    Like findById(BigInteger productId, BigInteger userId);

    void save(BigInteger productId, BigInteger userId);

}

