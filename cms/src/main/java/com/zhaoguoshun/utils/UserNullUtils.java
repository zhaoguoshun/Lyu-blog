package com.zhaoguoshun.utils;

import com.zhaoguoshun.entity.User;

public class UserNullUtils {

    public static User userIsNull(){
        User nullUser = new User();
        nullUser.setUserName("aaaaa");
        nullUser.setAvatar("http://shunxinblog.oss-cn-hangzhou.aliyuncs.com/2020/12/30/c1d5fb12c96c48f0b10d1b5764b73ecd.jpg");
        nullUser.setNickName("匿名");
        return nullUser;
    }
}
