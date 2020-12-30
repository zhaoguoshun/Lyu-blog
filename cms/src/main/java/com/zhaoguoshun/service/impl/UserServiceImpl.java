package com.zhaoguoshun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.User;
import com.zhaoguoshun.mapper.UserMapper;
import com.zhaoguoshun.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoguoshun.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    public int create(User user){
        return userMapper.create(user);
    }

    public int delete(Integer id){
        return userMapper.delete(Maps.build(id).getMap());
    }

    public int update(User user){
        System.out.println(user);

        System.out.println(Maps.build().beanToMap(user));
        return userMapper.update(Maps.build(user.getId()).beanToMapForUpdate(user));
    }

    public PageInfo<User> query(User user){
        if (user!=null && user.getPage() != null){
            PageHelper.startPage(user.getPage(),user.getLimit());
        }
        List<User> list = userMapper.query(Maps.build().beanToMap(user));
        for (int i=0; i<list.size();i++){
            String status = list.get(i).getStatus();
            if (status.equals("T")){
                list.get(i).setStatus("正常");
            }
            if (status.equals("F")){
                list.get(i).setStatus("禁用");
            }
            if (status.equals("D")){
                list.get(i).setStatus("待删除");
            }
        }

        return new PageInfo<> (list);
    }

    public User login(String userName,String password ){

        System.out.println("serviceName"+Maps.build()
                .put("userName",userName)
                .put("password",password)
                .getMap());
        return userMapper.detail(Maps.build()
                .put("userName",userName)
                .put("password",password)
                .getMap());
    }

    public User detail(Integer id){
        return userMapper.detail(Maps.build(id).getMap());
    }

    public int count(User user){
        return userMapper.count(Maps.build().beanToMap(user));
    }

}
