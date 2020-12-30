package com.zhaoguoshun.controller;

import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.Tag;
import com.zhaoguoshun.service.impl.TagServiceImpl;
import com.zhaoguoshun.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagServiceImpl tagServiceImpl;

    @PostMapping("/create")
    public Result create(@RequestBody Tag tag){
        tagServiceImpl.create(tag);
        return Result.ok(tag);
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        tagServiceImpl.delete(id);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody  Tag tag){

        System.out.println("我是update");
        System.out.println("tag====>"+tag);
        tagServiceImpl.update(tag);
        return Result.ok(tag);
    }
    @PostMapping("/query")
    public Map query(@RequestBody Tag tag){
        PageInfo<Tag> pageInfo = tagServiceImpl.query(tag);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }


    @PostMapping("/all")
    public Result all(Tag tag){
        return Result.ok(tagServiceImpl.all()) ;
    }


    @PostMapping("/detail")
    public Result detail(Integer id){
        Tag detail = tagServiceImpl.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Tag tag){
        int count = tagServiceImpl.count(tag);
        return Result.ok(count);
    }
}
