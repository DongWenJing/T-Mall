package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.Notice;
import com.tmall.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //获取公告
    @GetMapping
    public ResponseData findNotice() {
        Notice notice =  noticeService.findNotice();
        return ResponseDataUtils.buildSuccess("0", "获取公告成功！", notice);
    }

    //公告保存
    @PatchMapping
    public ResponseData updateNotice(@RequestBody Notice notice){
        noticeService.setNotice(notice);
        return ResponseDataUtils.buildSuccess("0", "公告保存成功",notice);
    }
}
