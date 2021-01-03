package com.zhaoguoshun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.*;
import com.zhaoguoshun.entity.Article;
import com.zhaoguoshun.mapper.*;
import com.zhaoguoshun.mapper.ArticleMapper;
import com.zhaoguoshun.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoguoshun.utils.Maps;
import com.zhaoguoshun.utils.UserNullUtils;
import com.zhaoguoshun.vo.ArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleAttachmentMapper articleAttachmentMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    public int create(Article article){
        article.setArticleView(0);
        int row = articleMapper.create(article);
        article.getArticleAttachments().forEach(entity->{
            ArticleAttachment articleAttachment = new ArticleAttachment();
            articleAttachment.setArticleId(article.getId());
            articleAttachment.setDescription(entity.get("name")+"");
            articleAttachment.setUrl(entity.get("url")+"");
            articleAttachmentMapper.create(articleAttachment);
        });

        article.getSelectTagList().forEach(tag->{
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagId(tag);
            articleTagMapper.create(articleTag);
        });
        return row;
    }

    public int delete(Integer id){
        int delete = articleMapper.delete(Maps.build(id).getMap());
        articleAttachmentMapper.delete(Maps.build().put("articleId",id).getMap());
        articleTagMapper.delete(Maps.build().put("articleId",id).getMap());
        commentMapper.delete(Maps.build().put("articleId",id).getMap());
        return delete;
    }

    public int update(Article article){
        int update = articleMapper.update(Maps.build(article.getId()).beanToMapForUpdate(article));
        articleAttachmentMapper.delete(Maps.build().put("articleId",article.getId()).getMap());
        articleTagMapper.delete(Maps.build().put("articleId",article.getId()).getMap());
        article.getArticleAttachments().forEach(entity->{
            ArticleAttachment articleAttachment = new ArticleAttachment();
            articleAttachment.setArticleId(article.getId());
            articleAttachment.setDescription(entity.get("name")+"");
            articleAttachment.setUrl(entity.get("url")+"");
            articleAttachmentMapper.create(articleAttachment);
        });
        article.getSelectTagList().forEach(tag->{
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagId(tag);
            articleTagMapper.create(articleTag);
        });
        return update;
    }

    public PageInfo<Article> query(Article article){
        if (article!=null && article.getPage() != null){
            PageHelper.startPage(article.getPage(),article.getLimit());
        }
        List<Article> list = articleMapper.query(Maps.build().beanToMap(article));
        for (Article article1 : list) {
            User user = userMapper.detail(Maps.build(article1.getCreateUser()).getMap());
            Channel channel = channelMapper.detail(Maps.build(article1.getChannelId()).getMap());
            article1.setChannel(channel);
            if (user!=null){
                article1.setUser(user);
            }else {
                article1.setUser(UserNullUtils.userIsNull());
            }
        }

        return new PageInfo<> (list);
    }

    public List<Article> orderBy(){
        return  articleMapper.olderBy(Maps.build().beanToMap(new Article()));
    }


    public List<Article> getChannelPos(Integer id){
        Article article = new Article();
        article.setChannelId(id);
        List<Article> list = articleMapper.ArticlePosQuery(Maps.build().beanToMap(article));
        for (Article article1 : list) {
            User user = userMapper.detail(Maps.build(article1.getCreateUser()).getMap());
            if (user!=null){
                article1.setUser(user);
            }else {
                article1.setUser(UserNullUtils.userIsNull());
            }
        }
        return list;
    }

    public PageInfo<Article> getPage(Article article,Integer number){
        if (number!=null){
            PageHelper.startPage(number,8);
        }
         List<Article> list = articleMapper.query(Maps.build().beanToMap(article));
        for (Article article1 : list) {
            User user = userMapper.detail(Maps.build(article1.getCreateUser()).getMap());
            Channel channel = channelMapper.detail(Maps.build(article1.getChannelId()).getMap());
            article1.setChannel(channel);
            if (user!=null){
                article1.setUser(user);
            }else {
                article1.setUser(UserNullUtils.userIsNull());
            }
        }
        return new PageInfo<> (list);
    }

    public List<Article> getRandomArticle(){
        return articleMapper.getRandomArticle();
    }

    public List<Article> top(Article article,Integer top){
        PageHelper.startPage(0,top);
        List<Article> list = articleMapper.query(Maps.build().beanToMap(article));
        return list;
    }

    public List<Article> top(){
        Article article = new Article();
        article.setTop(1);
        List<Article> list = articleMapper.query(Maps.build().beanToMap(article));
        for (Article article1 : list) {
            User user = userMapper.detail(Maps.build(article1.getCreateUser()).getMap());
            Channel channel = channelMapper.detail(Maps.build(article1.getChannelId()).getMap());
            article1.setChannel(channel);
            if (user!=null) {
                article1.setUser(user);
            }else {
                article1.setUser(UserNullUtils.userIsNull());
            }
        }
        return list;
    }


    public Article detail(Article a){
        Integer id=a.getId();
        Article article = articleMapper.detail(Maps.build(id).getMap());
        //查找发布人信息
        if (article!=null){
            User user = userMapper.detail(Maps.build(article.getCreateUser()).getMap());
            if (user!=null){
                article.setUser(user);
            }
        }
        //查询标签信息
        List<ArticleTag> articleTagList = articleTagMapper.query(Maps.build().put("articleId", id).getMap());
        List<ArticleAttachment> articleAttachments = articleAttachmentMapper.query(Maps.build().put("articleId", id).getMap());
        List<Integer> tags =new ArrayList<>();
        //查询栏目信息
        List<Map<String,Object>> articleAttachmentList =new ArrayList<>();
        articleTagList.forEach(entity->{
            tags.add(entity.getTagId());
        });
        articleAttachments.forEach(entity->{
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",entity.getDescription());
            map.put("url",entity.getUrl());
            articleAttachmentList.add(map);
        });
        article.setSelectTagList(tags);
        article.setArticleAttachments(articleAttachmentList);

        //增加视图
        if (a.getFront()){
            Article viewArticle = new Article();
            viewArticle.setArticleView(article.getArticleView()+1);
            article.setArticleView(viewArticle.getArticleView());
            articleMapper.update(Maps.build(id).beanToMapForUpdate(viewArticle));
        }
        //猜你喜欢 查询6条标签
        if(articleTagList.size()!=0){
            Integer tagId = articleTagList.get(0).getTagId();
            //tag
            List<ArticleTag> listTag = articleTagMapper.getLike(Maps.build().put("tagId", tagId).getMap());
            List<ArticleVo> articleVos = new ArrayList<>();
            for (ArticleTag articleTag : listTag) {
                Article article1 = new Article();
                article1.setId(articleTag.getArticleId());
                Article detail = articleMapper.detail(Maps.build(articleTag.getArticleId()).getMap());
                ArticleVo articleVo = new ArticleVo();
                if (detail!=null) {
                    BeanUtils.copyProperties(detail,articleVo);
                    //随机查出两条数据
                    articleVos.add(articleVo);
                }
            }
            article.setArticleVo(articleVos);
        }
        return article;
    }


    public List<Article> getNotice(Article article){
        return articleMapper.getNotice(Maps.build().beanToMap(article));
    }



    public int count(Article article){
        return articleMapper.count(Maps.build().beanToMap(article));
    }
}
