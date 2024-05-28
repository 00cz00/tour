package com.example.tour.controller;

import com.example.tour.result.Result;
import com.example.tour.utils.AliYunOssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;
@Slf4j
@RestController
public class CommonController {

    //文件上传
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀   dfdfdf.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;

            //文件的请求路径
            String filePath = AliYunOssUtils.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }

        return Result.error("文件上传失败");
    }



    //上传图片到oss并返回url
    @PostMapping("/deleteAliOss")
    public void deleteImageToOSS(String url) {

        System.out.println(url);
        String[] arr=url.split("/");
        System.out.println(arr[3]);
        AliYunOssUtils.delete(arr[3]);

    }
}