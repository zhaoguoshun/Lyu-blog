package com.zhaoguoshun.mapper;

import com.zhaoguoshun.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TagMapper {

    int create(Tag tag);

    int delete(Map<String,Object> paraMap);

    int update(Map<String,Object> paraMap);

    List<Tag> query(Map<String,Object> paramMap);

    Tag detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
