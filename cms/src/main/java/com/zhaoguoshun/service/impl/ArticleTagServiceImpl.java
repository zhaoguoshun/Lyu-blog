package com.zhaoguoshun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.Article;
import com.zhaoguoshun.entity.ArticleTag;
import com.zhaoguoshun.entity.Channel;
import com.zhaoguoshun.entity.User;
import com.zhaoguoshun.mapper.ArticleMapper;
import com.zhaoguoshun.mapper.ArticleTagMapper;
import com.zhaoguoshun.mapper.ChannelMapper;
import com.zhaoguoshun.mapper.UserMapper;
import com.zhaoguoshun.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoguoshun.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章标签 服务实现类
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ChannelMapper channelMapper;


    public int create(ArticleTag articleTag){
        return articleTagMapper.create(articleTag);
    }

    public int delete(Integer id){
        return articleTagMapper.delete(Maps.build(id).getMap());
    }

    public int update(ArticleTag articleTag){
        return articleTagMapper.update(Maps.build(articleTag.getId()).beanToMapForUpdate(articleTag));
    }

    public PageInfo<ArticleTag> query(ArticleTag articleTag){
        if (articleTag!=null && articleTag.getPage() != null){
            PageHelper.startPage(articleTag.getPage(),articleTag.getLimit());
        }
        List<ArticleTag> list = articleTagMapper.query(Maps.build().beanToMap(articleTag));
        return new PageInfo<> (list);
    }

    public PageInfo<ArticleTag> getArticleId(ArticleTag articleTag){
        if (articleTag!=null && articleTag.getPage() != null){
            PageHelper.startPage(articleTag.getPage(),articleTag.getLimit());
        }
        List<ArticleTag> list = articleTagMapper.query(Maps.build(articleTag.getTagId()).beanToMap(articleTag));

        for (int i=0 ; i<list.size();i++){
            Article article = articleMapper.detail(Maps.build(list.get(i).getArticleId()).getMap());
            User user = userMapper.detail(Maps.build(article.getCreateUser()).getMap());
            Channel channel = channelMapper.detail(Maps.build(article.getChannelId()).getMap());
            if (user!=null){
                article.setUser(user);
            }
            article.setChannel(channel);
            list.get(i).setArticle(article);
        }
        return new PageInfo<> (list);
    }

    public ArticleTag detail(Integer id){
        return articleTagMapper.detail(Maps.build(id).getMap());
    }

    public int count(ArticleTag articleTag){
        return articleTagMapper.count(Maps.build().beanToMap(articleTag));
    }
}
