package com.yjxxt.am.mapper;

import com.yjxxt.am.base.BaseMapper;
import com.yjxxt.am.bean.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByUserName(String userName);

    Integer updateByPrimaryKey(User user);

    /*用户管理--添加用户--查看添加用户是否存在*/
    User selectUserByName(String userName);
}