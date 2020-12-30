package com.zhaoguoshun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.Article;
import com.zhaoguoshun.entity.Channel;
import com.zhaoguoshun.entity.Channel;
import com.zhaoguoshun.entity.User;
import com.zhaoguoshun.mapper.ArticleMapper;
import com.zhaoguoshun.mapper.ChannelMapper;
import com.zhaoguoshun.mapper.ChannelMapper;
import com.zhaoguoshun.mapper.UserMapper;
import com.zhaoguoshun.service.ChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoguoshun.service.UserService;
import com.zhaoguoshun.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 栏目 服务实现类
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ArticleServiceImpl articleService;


    public int create(Channel channel){
        return channelMapper.create(channel);
    }

    public int delete(Integer id){
        return channelMapper.delete(Maps.build(id).getMap());
    }

    public int update(Channel channel){
        System.out.println(channel);

        System.out.println(Maps.build().beanToMap(channel));
        return channelMapper.update(Maps.build(channel.getId()).beanToMapForUpdate(channel));
    }

    public PageInfo<Channel> query(Channel channel){
        if (channel!=null && channel.getPage() != null){
            PageHelper.startPage(channel.getPage(),channel.getLimit());
        }
        List<Channel> list = channelMapper.query(Maps.build().beanToMap(channel));
        for (int i=0; i<list.size();i++){
            if (list.get(i).getCreateUser()!=null){
                User user = userService.detail(list.get(i).getCreateUser());
                list.get(i).setUserName(user.getNickName());
            }
            String single = list.get(i).getSingle();
            String pos = list.get(i).getPos();
            if (single!=null){
                if (single.equals("Y")){
                    list.get(i).setSingle("单页");
                }
                if (single.equals("N")){
                    list.get(i).setSingle("不单页");
                }
            }
            if (pos!=null){
                if (pos.equals("A")){
                    list.get(i).setPos("导航");
                }
                if (pos.equals("B")){
                    list.get(i).setPos("中间表格");
                }
                if (pos.equals("C")){
                    list.get(i).setPos("公告");
                }
            }
        }
        return new PageInfo<Channel> (list);
    }


    public PageInfo<Channel> NoByQuery(Channel channel){
        if (channel!=null && channel.getPage() != null){
            PageHelper.startPage(channel.getPage(),4);
        }
        List<Channel> list = channelMapper.query(Maps.build().beanToMap(channel));
        for (int i=0; i<list.size();i++){
            if (list.get(i).getCreateUser()!=null){
                User user = userService.detail(list.get(i).getCreateUser());
                System.out.println("现在打印一下user"+user.getNickName());
                list.get(i).setUserName(user.getNickName());
            }
        }
        return new PageInfo<Channel> (list);
    }



    public List<Channel> getChannelPos(String  pos){
        List<Channel> list = channelMapper.query(Maps.build().put("pos", pos).getMap());

        return list;
    }

    public List<Channel> all(){
        List<Channel> list = channelMapper.query(Maps.build().getMap());
        return list;
    }


    public Channel detail(Integer id){
        //查找发布人
        return channelMapper.detail(Maps.build(id).getMap());
    }

    public int count(Channel channel){
        return channelMapper.count(Maps.build().beanToMap(channel));
    }
}
