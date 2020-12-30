package com.zhaoguoshun.controller;


import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.ArticleView;
import com.zhaoguoshun.service.impl.ArticleViewServiceImpl;
import com.zhaoguoshun.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 文章浏览记录 前端控制器
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/article-view")
public class ArticleViewController {
    @Autowired
    private ArticleViewServiceImpl articleViewServiceImpl;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleView articleView){
        articleViewServiceImpl.create(articleView);
        return Result.ok(articleView);
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        articleViewServiceImpl.delete(id);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody  ArticleView articleView){

        System.out.println("我是update");
        System.out.println("articleView====>"+articleView);
        articleViewServiceImpl.update(articleView);
        return Result.ok(articleView);
    }
    @PostMapping("/query")
    public Map query(@RequestBody ArticleView articleView){
        PageInfo<ArticleView> pageInfo = articleViewServiceImpl.query(articleView);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        ArticleView detail = articleViewServiceImpl.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleView articleView){
        int count = articleViewServiceImpl.count(articleView);
        return Result.ok(count);
    }
}

