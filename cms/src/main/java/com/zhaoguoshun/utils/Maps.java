package com.zhaoguoshun.utils;


import com.zhaoguoshun.entity.User;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Map工具类，让Map支持链式调用
 */
public class Maps {

    private Map<String,Object>  paramMap = new HashMap<>();

    private Maps(){

    }
    private Maps(String key,String value){
        paramMap.put(key,value);
    }

    private Maps(String key,Object value){
        paramMap.put(key,value);
    }

    private Maps(Integer id){
        paramMap.put("id",id);
    }

    //1、build方法目的是构建Maps对象，因为Maps对象有一个Map集合（支持构建传参数）
    public static Maps build(String key,String value){
        return new Maps(key,value);
    }
    //2、build方法目的是构建Maps对象，因为Maps对象有一个Map集合（构建简单Map对象）
    public static Maps build(){
        return new Maps();
    }

    //3、直接传id构建map
    public static Maps build(Integer id){
        return new Maps(id);
    }

    //向Map集合中追加key-value
    public Maps put(String key,Object value){
        return add(key,value);
    }

    public Maps add(String key,Object value){
        paramMap.put(key,value);
        return this;
    }

    //向Map集合中追加ID
    public Maps addId(Integer id){
        paramMap.put("id",id);
        return this;
    }
    public Maps putId(Integer id){
        return addId(id);
    }

    //向Map集合中追加Map对象
    public Maps add(Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }
    public Maps put(Map<String,Object> map){
        return add(map);
    }

    //获取Map最终集合
    public Map<String,Object> getMap(){
        return paramMap;
    }

    //对象转Map集合
    public <T> Map<String,Object> beanToMapForUpdate(T bean){
        if(bean != null){
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                paramMap.put("update"+upperFirstLetter(key+""),beanMap.get(key));
            }
        }
        return paramMap;
    }

    //对象转Map集合
    public <T> Map<String,Object> beanToMap(T bean){
        if(bean != null){
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                paramMap.put(key+"",beanMap.get(key));
            }
        }
        return paramMap;
    }

    public static String upperFirstLetter(String str){
        char[] chars = str.toCharArray();
        if(chars[0]>='a' && chars[0]<='z'){
            chars[0] = (char)(chars[0]-32);
        }
        return new String(chars);
    }

    public static void main(String[] args) {

        User user = new User();
        user.setId(1);
        user.setPassword("zaa");
        Maps maps = new Maps();
        System.out.println(maps.beanToMap(user));;
        System.out.println(Maps.build().beanToMapForUpdate(user));;
    }

}
