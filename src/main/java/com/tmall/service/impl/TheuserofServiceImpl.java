package com.tmall.service.impl;

import com.tmall.mapper.TheuserofMapper;
import com.tmall.pojo.RoleCount;
import com.tmall.service.TheuserofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheuserofServiceImpl implements TheuserofService {
    @Autowired
    private TheuserofMapper theuserofMapper;

    //用户组成
    @Override
    public RoleCount getRoleCount() {
        return theuserofMapper.getRoleCount();
    }
}
