package com.zhaoguoshun.mapper;

import com.zhaoguoshun.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhaoguoshun.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Repository
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    int create(Comment comment);

    int delete(Map<String,Object> paraMap);

    int update(Map<String,Object> paraMap);

    List<Comment> query(Map<String,Object> paramMap);

    Comment detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
