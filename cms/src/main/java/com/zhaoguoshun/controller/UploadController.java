package com.zhaoguoshun.controller;

import com.zhaoguoshun.utils.RequestUtils;
import com.zhaoguoshun.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @PostMapping("/uploadFile")
    public Result upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("打印一下返回路径=====》"+RequestUtils.getBasePath(request));
        System.out.println("打印一下返回路径=====》"+RequestUtils.getBasePath(request));
        System.out.println("打印一下返回路径=====》"+RequestUtils.getBasePath(request));
        String originalFilename = file.getOriginalFilename();
        //获取文件名后缀
        String ex = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
        String newFileNamePrefix= UUID.randomUUID().toString();
        String newFileName=newFileNamePrefix+"."+ex;
        ///usr/LyuBlog/upload
        file.transferTo(new File("/upload/cms",newFileName));
        //最后返回的是一个可以访问的全路径
        return Result.ok(RequestUtils.getBasePath(request)+"upload/"+newFileName);
    }
}
