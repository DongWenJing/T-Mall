package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author R.Yu
 * @date 2022/3/21 22:28
 */
@RestController
@CrossOrigin
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseData<?> upload(MultipartFile file) throws IOException {
        return fileService.upload(file);
    }

    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) throws IOException {
        fileService.getFiles(flag, response);
    }
}
