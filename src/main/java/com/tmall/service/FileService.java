package com.tmall.service;

import com.tmall.common.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author R.Yu
 * @date 2022/3/21 15:59
 */
public interface FileService {
    ResponseData<?> upload(MultipartFile file) throws IOException;
    void getFiles(String flag, HttpServletResponse response) throws IOException;
}
