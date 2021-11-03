package com.yjxxt.am.mapper;

import com.yjxxt.am.base.BaseMapper;
import com.yjxxt.am.bean.Role;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role,Integer> {


    /*查询所有的角色*/
    /*用户管理--添加修改角色--加载角色信息*/
    @MapKey("")
    public List<Map<String,Object>> selectRoles(Integer userId);


    /*角色数据报表*/
    List<Map<String, Object>> findRoleType();
}