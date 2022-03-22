package com.tmall.service.impl;

import cn.hutool.core.io.FileUtil;
import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.exception.IllegalImageException;
import com.tmall.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import javax.servlet.http.HttpServletResponse;

/**
 * @author R.Yu
 * @date 2022/3/21 16:10
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${server.port}")
    private String port;

    public static final String ip = "http://127.0.0.1";

    @Override
    public ResponseData<?> upload(MultipartFile file) throws IllegalImageException, IOException {
        // 1.确保为图片
        String fileName = file.getOriginalFilename();
        BufferedImage bufferedImage =
                ImageIO.read(file.getInputStream());
        if (!fileName.toLowerCase().matches("^.+\\.(jpg|png|gif)$"))
            throw new IllegalImageException("不支持的图片格式");
        if ( bufferedImage.getHeight() == 0
                || bufferedImage.getWidth() == 0)
            throw new IllegalImageException("非法图片");
        // 目录存储  提交的时间+哈希值+名字
        String dateStr =
                new SimpleDateFormat("yyyyMMdd").format(new Date());
        String uuid = UUID.randomUUID().toString();
        String flag = new StringBuilder().append(dateStr).
                append(uuid).append(fileName).toString();
        String rootFilePath = System.getProperty("user.dir") + "/resources/files/" + flag;
        // 查看文件位置
        System.out.println("rootFilePath = " + rootFilePath);

        FileUtil.writeBytes(file.getBytes(),rootFilePath);
        String url = String.format("%s:%s/files/%s", ip,port,flag);
        return ResponseDataUtils.buildSuccess("0", "成功", url);
    }

    public void getFiles(String flag,HttpServletResponse response) throws IOException {
        // 文件上传的根路径
        String basePath = System.getProperty("user.dir") + "/resources/files/";
        // 获取所有文件名称
        List<String> listFileNames = FileUtil.listFileNames(basePath);
        // 找到和参数flag一致的文件(flag)
        String fileName = listFileNames.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains(flag);
            }
        }).findAny().orElse("");
        if (StringUtils.hasLength(fileName)) {
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-stream");
            // 通过文件的路径读取文件字节流
            byte[] bytes = FileUtil.readBytes(basePath + fileName);
            // 通过输出流返回文件
            OutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        }
    }

}
