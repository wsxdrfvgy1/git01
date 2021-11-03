package com.yjxxt.am.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.am.base.BaseService;
import com.yjxxt.am.bean.Role;
import com.yjxxt.am.mapper.RoleMapper;
import com.yjxxt.am.query.RoleQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoleService extends BaseService<Role, Integer> {

    @Autowired(required = false)
    private RoleMapper roleMapper;


    /**
     * 查询所有的角色信息
     * 用户管理--添加修改角色--加载角色信息
     *
     * @return
     */
    public List<Map<String, Object>> findRoles(Integer userId) {
        return roleMapper.selectRoles(userId);
    }

    /**
     * 角色管理页面--查询数据
     *
     *
     */
    public Map<String, Object> findRoleByParam(RoleQuery roleQuery) {
        //实例化Map
        Map<String, Object> map = new HashMap<String, Object>();
        //开启分页单位
        PageHelper.startPage(roleQuery.getPage(), roleQuery.getLimit());
        PageInfo<Role> rlist = new PageInfo<>(selectByParams(roleQuery));
        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", rlist.getTotal());
        map.put("data", rlist.getList());
        //返回目标map
        return map;
    }


    /*角色数据报表*/
    public List<Map<String, Object>> findRoleType() {
        return roleMapper.findRoleType();
    }
}
