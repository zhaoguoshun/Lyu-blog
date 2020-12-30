package com.zhaoguoshun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.ArticleView;
import com.zhaoguoshun.entity.ArticleView;
import com.zhaoguoshun.mapper.ArticleViewMapper;
import com.zhaoguoshun.mapper.ArticleViewMapper;
import com.zhaoguoshun.service.ArticleViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoguoshun.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章浏览记录 服务实现类
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Service
public class ArticleViewServiceImpl extends ServiceImpl<ArticleViewMapper, ArticleView> implements ArticleViewService {

    @Autowired
    private ArticleViewMapper articleViewMapper;


    public int create(ArticleView articleView){
        return articleViewMapper.create(articleView);
    }

    public int delete(Integer id){
        return articleViewMapper.delete(Maps.build(id).getMap());
    }

    public int update(ArticleView articleView){
        System.out.println(articleView);

        System.out.println(Maps.build().beanToMap(articleView));
        return articleViewMapper.update(Maps.build(articleView.getId()).beanToMapForUpdate(articleView));
    }

    public PageInfo<ArticleView> query(ArticleView articleView){
        if (articleView!=null && articleView.getPage() != null){
            PageHelper.startPage(articleView.getPage(),articleView.getLimit());
        }
        List<ArticleView> list = articleViewMapper.query(Maps.build().beanToMap(articleView));
        return new PageInfo<ArticleView> (list);
    }

    public ArticleView detail(Integer id){
        return articleViewMapper.detail(Maps.build(id).getMap());
    }

    public int count(ArticleView articleView){
        return articleViewMapper.count(Maps.build().beanToMap(articleView));
    }

}
