package com.zhaoguoshun.mapper;

import com.zhaoguoshun.entity.ArticleAttachment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhaoguoshun.entity.ArticleAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章附件 Mapper 接口
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Repository
@Mapper
public interface ArticleAttachmentMapper extends BaseMapper<ArticleAttachment> {
    int create(ArticleAttachment articleAttachment);

    int delete(Map<String,Object> paraMap);

    int update(Map<String,Object> paraMap);

    List<ArticleAttachment> query(Map<String,Object> paramMap);

    ArticleAttachment detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);

}
