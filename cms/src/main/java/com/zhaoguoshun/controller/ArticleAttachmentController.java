package com.zhaoguoshun.controller;


import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.ArticleAttachment;
import com.zhaoguoshun.service.impl.ArticleAttachmentServiceImpl;
import com.zhaoguoshun.service.impl.TagServiceImpl;
import com.zhaoguoshun.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 文章附件 前端控制器
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/article-attachment")
public class ArticleAttachmentController {
    @Autowired
    private ArticleAttachmentServiceImpl articleAttachmentService;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleAttachment articleAttachmen){
        articleAttachmentService.create(articleAttachmen);
        return Result.ok(articleAttachmen);
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        articleAttachmentService.delete(id);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody  ArticleAttachment articleAttachmen){

        System.out.println("我是update");
        System.out.println("articleAttachmen====>"+articleAttachmen);
        articleAttachmentService.update(articleAttachmen);
        return Result.ok(articleAttachmen);
    }
    @PostMapping("/query")
    public Map query(@RequestBody ArticleAttachment articleAttachmen){
        PageInfo<ArticleAttachment> pageInfo = articleAttachmentService.query(articleAttachmen);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        ArticleAttachment detail = articleAttachmentService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleAttachment articleAttachmen){
        int count = articleAttachmentService.count(articleAttachmen);
        return Result.ok(count);
    }
}

