package com.zhaoguoshun.mapper;

import com.zhaoguoshun.entity.FriendLink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhaoguoshun.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 友情链接 Mapper 接口
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Repository
@Mapper
public interface FriendLinkMapper extends BaseMapper<FriendLink> {
    int create(FriendLink friendLink);

    int delete(Map<String,Object> paraMap);

    int update(Map<String,Object> paraMap);

    List<FriendLink> query(Map<String,Object> paramMap);

    FriendLink detail(Map<String,Object> paramMap);

    int count(Map<String,Object> paramMap);
}
