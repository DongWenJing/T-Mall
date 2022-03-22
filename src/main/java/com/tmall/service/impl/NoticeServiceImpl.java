package com.tmall.service.impl;

import com.tmall.mapper.NoticeMapper;
import com.tmall.pojo.Notice;
import com.tmall.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    //获取公告
    @Override
    public Notice findNotice() {
        return noticeMapper.findNotice();
    }

    //保存公告
    @Override
    public void setNotice(Notice notice) {
        noticeMapper.setNotice(notice);
    }
}
