package com.tmall.service;

import com.tmall.pojo.Notice;

public interface NoticeService {
    //获取公告
    Notice findNotice();

    //保存公告
    void setNotice(Notice notice);
}
