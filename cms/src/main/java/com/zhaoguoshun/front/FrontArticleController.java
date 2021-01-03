package com.zhaoguoshun.front;


import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.Article;
import com.zhaoguoshun.entity.Comment;
import com.zhaoguoshun.service.impl.ArticleServiceImpl;
import com.zhaoguoshun.service.impl.CommentServiceImpl;
import com.zhaoguoshun.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/front/article")
public class FrontArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private CommentServiceImpl commentService;
    @GetMapping("/get")
    public Result getById(Integer id){
        Article article = new Article();
        article.setId(id);
        article.setFront(true);
        Article detail = articleService.detail(article);
        return Result.ok(detail);
    }

    @GetMapping("/getLikeArticle")
    public Result getLikeArticle(Integer id){
        Article article = new Article();
        article.setId(id);
        article.setFront(false);
        Article detail = articleService.detail(article);
        return Result.ok(detail);
    }


    @GetMapping("/getByChannelId")
    public Map getByIdChannel(Article article,Integer page){
        return Result.ok(articleService.getPage(article,page));
    }
    /**
     * 点击排行
     * @return
     */
    @GetMapping("/getOlderByArticle")
    public Result getOlderArticle(){
        return Result.ok(articleService.orderBy());
    }

    @GetMapping("/getNotice")
    public Result getNotice(Integer channelId){
        Article article = new Article();
        article.setChannelId(channelId);
        List<Article> list = articleService.getNotice(article);
        return Result.ok(list);
    }

    /**
     * 分页查询文章
     * @return
     */
    @GetMapping("/page")
    public Map pageArticleList(Integer number,Article article){
        return Result.ok(articleService.getPage(article,number));
    }

    @GetMapping("/getTop")
    public Result getById(Article article,Integer top){
        return Result.ok(articleService.top(article, top));
    }

    @GetMapping("/Top")
    public Result getById(){
        return Result.ok(articleService.top());
    }

    /**
     * 查询文章评论
     * @param articleId
     * @return
     */
    @GetMapping("/getCommentArticleId")
    public Result getCommentArticleId(Integer articleId){
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setStatus(0);
        return Result.ok( commentService.getCommentArticle(comment));
    }

    //查询出随机的两条数据

    @GetMapping("/getRandomArticle")
    public Result getRandomArticle(){
        return Result.ok(articleService.getRandomArticle());
    }

    @PostMapping("/search")
    public Map search(@RequestBody Article article){
        PageInfo<Article> pageInfo = articleService.query(article);
        return Result.ok(pageInfo);
    }


}
