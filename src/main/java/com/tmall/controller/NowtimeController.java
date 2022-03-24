package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.service.NowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;

@RequestMapping("/countdown")
@RestController
@CrossOrigin
public class NowtimeController {

    @Autowired
    private NowtimeService nowtimeService;

//获取时间
    @GetMapping
    public ResponseData<?> getTime() {
        Timestamp time = nowtimeService.getTime1();
        return ResponseDataUtils.buildSuccess("0", "获取时间成功",time);
    }
//修改时间
     @PatchMapping("/{newTime}")
    public ResponseData changedTime(@PathVariable String newTime){
        nowtimeService.changedTime(newTime);
        return ResponseDataUtils.buildSuccess("0","修改时间成功",newTime);
     }

}
