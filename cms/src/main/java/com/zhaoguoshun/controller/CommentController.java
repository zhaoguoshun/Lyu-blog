package com.zhaoguoshun.controller;


import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.Article;
import com.zhaoguoshun.entity.Comment;
import com.zhaoguoshun.service.impl.CommentServiceImpl;
import com.zhaoguoshun.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @PostMapping("/create")
    public Result create(@RequestBody Comment comment){
        commentServiceImpl.create(comment);
        return Result.ok("评论成功,待管理员审核",comment);
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        commentServiceImpl.delete(id);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody  Comment comment){
        commentServiceImpl.update(comment);
        return Result.ok(comment);
    }
    @PostMapping("/query")
    public Map query(@RequestBody Comment comment){
        PageInfo<Comment> pageInfo = commentServiceImpl.query(comment);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }


    @PostMapping("/detail")
    public Result detail(Integer id){
        Comment detail = commentServiceImpl.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Comment comment){
        int count = commentServiceImpl.count(comment);
        return Result.ok(count);
    }

    /**
     * 查询出所有状态为1的评论
     * @return
     */
    @PostMapping("/getStatusComment")
    public Map getStatusComment(Comment comment){
        System.out.println("评论的内容"+comment);
        comment.setStatus(1);
        PageInfo<Comment> pageInfo = commentServiceImpl.query(comment);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    //分页查询出所有状态为1的评论
    @GetMapping("/getPageCommentId")
    public Map getStatusComment(Integer pageId){
        Comment comment = new Comment();
        System.out.println("评论的内容"+pageId);
        comment.setStatus(1);
        comment.setPage(pageId);
        PageInfo<Comment> pageInfo = commentServiceImpl.query(comment);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    /**
     * 审核通过
     */

    @GetMapping("/getUpdateStatus")
    public Result getUpdateStatus(Integer commentId){
        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setStatus(0);
        commentServiceImpl.update(comment);
        return Result.ok();
    }

}

