package com.zhaoguoshun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.Article;
import com.zhaoguoshun.entity.Comment;
import com.zhaoguoshun.entity.Comment;
import com.zhaoguoshun.mapper.CommentMapper;
import com.zhaoguoshun.mapper.CommentMapper;
import com.zhaoguoshun.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoguoshun.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    public int create(Comment comment){
        comment.setStatus(1);
        return commentMapper.create(comment);
    }

    public int delete(Integer id){
        return commentMapper.delete(Maps.build(id).getMap());
    }

    public int update(Comment comment){
        System.out.println(comment);

        System.out.println(Maps.build().beanToMap(comment));
        return commentMapper.update(Maps.build(comment.getId()).beanToMapForUpdate(comment));
    }

    public PageInfo<Comment> query(Comment comment){
        if (comment!=null && comment.getPage() != null){
            PageHelper.startPage(comment.getPage(),comment.getLimit());
        }
        List<Comment> list = commentMapper.query(Maps.build().beanToMap(comment));

        for (int i=0;i<list.size();i++){
            if (list.get(i).getStatus()==1){
                list.get(i).setStatusName("待审核");
            }else {
                list.get(i).setStatusName("审核通过");
            }
        }
        return new PageInfo<> (list);
    }

    public List<Comment> getCommentArticle(Comment comment){
        List<Comment> list = commentMapper.query(Maps.build().beanToMap(comment));
        return list;
    }



    public Comment detail(Integer id){
        return commentMapper.detail(Maps.build(id).getMap());
    }

    public int count(Comment comment){
        return commentMapper.count(Maps.build().beanToMap(comment));
    }
}
