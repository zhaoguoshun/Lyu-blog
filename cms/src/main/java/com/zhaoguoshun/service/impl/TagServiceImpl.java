package com.zhaoguoshun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.Tag;
import com.zhaoguoshun.mapper.TagMapper;
import com.zhaoguoshun.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl {


    @Autowired
    private TagMapper tagMapper;


    public int create(Tag tag){
        return tagMapper.create(tag);
    }

    public int delete(Integer id){
        return tagMapper.delete(Maps.build(id).getMap());
    }

    public int update(Tag tag){
        System.out.println(tag);

        System.out.println(Maps.build().beanToMap(tag));
        return tagMapper.update(Maps.build(tag.getId()).beanToMapForUpdate(tag));
    }

    public PageInfo<Tag> query(Tag tag){
        if (tag!=null && tag.getPage() != null){
            PageHelper.startPage(tag.getPage(),tag.getLimit());
        }
        List<Tag> list = tagMapper.query(Maps.build().beanToMap(tag));
        return new PageInfo<Tag> (list);
    }


    public List<Tag> all(){
        List<Tag> list = tagMapper.query(null);
        return list;
    }

    public Tag detail(Integer id){
        return tagMapper.detail(Maps.build(id).getMap());
    }

    public int count(Tag tag){
        return tagMapper.count(Maps.build().beanToMap(tag));
    }

}
