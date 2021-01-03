package com.zhaoguoshun.front;

import com.github.pagehelper.PageInfo;
import com.zhaoguoshun.entity.Article;
import com.zhaoguoshun.entity.Channel;
import com.zhaoguoshun.service.impl.ArticleServiceImpl;
import com.zhaoguoshun.service.impl.ChannelServiceImpl;
import com.zhaoguoshun.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/front/channel")
public class FrontChannelController {
    @Autowired
    private ChannelServiceImpl channelService;

    @Autowired
    private ArticleServiceImpl articleService;
    @GetMapping("/get")
    public Result get(Integer id){
        if (id==null){
            return Result.fail();
        }
        Channel detail = channelService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/getChannelArticle")
    public Map getChannelArticle(@RequestBody Article article){
        PageInfo<Article> page = articleService.getPage(article,article.getPage());
        return Result.ok(page);
    }

    @GetMapping("/noByChannel")
    public Map getChannelArticle(Integer channelId){
        Channel channel = new Channel();
        channel.setParentId(channelId);
        return Result.ok(channelService.NoByQuery(channel));
    }



    @GetMapping("/getChannelArticlePos")
    public Result getChannelArticlePos(Integer channelId){
        List<Article> list = articleService.getChannelPos(channelId);
        return Result.ok(list);
    }


    @GetMapping("/queryByPos")
    public Result getChannelByPos(String pos){
        if (StringUtils.isEmpty(pos)){
            return Result.fail();
        }
        List<Channel> channelPos = channelService.getChannelPos(pos.toUpperCase());
        List<Map<String,Object>> mapList=new ArrayList<>();
        for (Channel channel : channelPos) {
            //首先获取顶级栏目
            if (channel.getParentId()==0){
                Map<String,Object> map =new HashMap<>();
                map.put("id",channel.getId());
                map.put("name",channel.getName());
                if (channel!= null &&"Y".equals(channel.getSingle())){
                    map.put("single",true);
                }
                List<Map<String,Object>> children=new ArrayList<>();
                for (Channel entity : channelPos) {
                    if (entity.getParentId() == channel.getId() ){
                        Map<String,Object> subMap=new HashMap<>();
                        subMap.put("id",entity.getId());
                        subMap.put("name",entity.getName());
                        if (entity!= null &&"Y".equals(entity.getSingle())){
                            map.put("single",true);
                        }
                        children.add(subMap);
                    }
                }
                if (children.size()>0){
                    map.put("children",children);
                }
                mapList.add(map);
            }
        }
        return Result.ok(mapList);
    }
}
