package com.zhaoguoshun.mapper;

import com.zhaoguoshun.entity.ArticleView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhaoguoshun.entity.ArticleView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章浏览记录 Mapper 接口
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Repository
@Mapper
public interface ArticleViewMapper extends BaseMapper<ArticleView> {
    int create(ArticleView articleView);

    int delete(Map<String,Object> paraMap);

    int update(Map<String,Object> paraMap);

    List<ArticleView> query(Map<String,Object> paramMap);

    ArticleView detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
