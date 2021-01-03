package com.zhaoguoshun.controller;


import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.ArticleTag;
import com.zhaoguoshun.service.impl.ArticleServiceImpl;
import com.zhaoguoshun.service.impl.ArticleTagServiceImpl;
import com.zhaoguoshun.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 文章标签 前端控制器
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/articleArticleTag")
public class ArticleTagController {
    @Autowired
    private ArticleTagServiceImpl articleTagService;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleTag articleTag){
        articleTagService.create(articleTag);
        return Result.ok(articleTag);
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        articleTagService.delete(id);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody  ArticleTag articleTag){
        articleTagService.update(articleTag);
        return Result.ok(articleTag);
    }
    @PostMapping("/query")
    public Map query(@RequestBody ArticleTag articleTag){
        PageInfo<ArticleTag> pageInfo = articleTagService.query(articleTag);
        return Result.ok(pageInfo);
    }

    @PostMapping("/getArticleTag")
    public Map getArticleTag(@RequestBody ArticleTag articleTag){
        System.out.println("进来进来");
        PageInfo<ArticleTag> pageInfo = articleTagService.getArticleId(articleTag);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        ArticleTag detail = articleTagService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleTag articleTag){
        int count = articleTagService.count(articleTag);
        return Result.ok(count);
    }
}

