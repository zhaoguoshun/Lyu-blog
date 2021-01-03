package com.zhaoguoshun.mapper;

import com.zhaoguoshun.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhaoguoshun.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Repository
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    int create(Article article);

    int delete(Map<String,Object> paraMap);

    int update(Map<String,Object> paraMap);
    List<Article> query(Map<String,Object> paramMap);

    List<Article> olderBy(Map<String,Object> paramMap);

    List<Article> ArticlePosQuery(Map<String,Object> paramMap);

    List<Article> getRandomArticle();

    List<Article> getNotice(Map<String,Object> paramMap);
    Article detail(Map<String,Object> paramMap);
    int count(Map<String,Object> paramMap);
}
