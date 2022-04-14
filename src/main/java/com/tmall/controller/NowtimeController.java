package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.service.NowtimeService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RequestMapping("/countdown")
@RestController
@CrossOrigin
public class NowtimeController {

//    @Autowired
//    private NowtimeService nowtimeService;

    private final NowtimeService nowtimeService;

    public NowtimeController(NowtimeService nowtimeService) {
        this.nowtimeService = nowtimeService;
    }

    //获取时间
    @GetMapping
    public ResponseData<?> getTime() {
        Timestamp time = nowtimeService.getTime();
        return ResponseDataUtils.buildSuccess("0", "获取时间成功",time);
    }

    //修改时间
    @PatchMapping("/{newTime}")
    public ResponseData<?> setCountdown(@PathVariable String newTime) {
        nowtimeService.setTime(newTime);
        return ResponseDataUtils.buildSuccess("0", "倒计时修改成功");
    }
}
