package com.tmall.controller;

import com.tmall.pojo.RoleCount;
import com.tmall.service.TheuserofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class TheuserofController {

    //成员组成
    @Autowired
    private TheuserofService theuserofService;

    @GetMapping("/role/count")
    public RoleCount getRoleCount(){
        return theuserofService.getRoleCount();
    }
}
