package com.yjxxt.am.controller;


import com.yjxxt.am.base.BaseController;
import com.yjxxt.am.query.RoleQuery;
import com.yjxxt.am.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;

    /**
     * 用户管理--添加修改角色--加载角色信息
     * @param userId
     * @return
     */
    @RequestMapping("findRoles")
    @ResponseBody
    public List<Map<String, Object>> sayRoles(Integer userId) {

        return roleService.findRoles(userId);
    }


    /**
     * 系统设置-角色管理页面数据
     * 角色查询的中转
     */
    @RequestMapping("index")
    public String index(){
        return  "role/role";
    }


    /**
     * 角色管理页面数据
     * 角色查询
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(RoleQuery roleQuery) {
        return roleService.findRoleByParam(roleQuery);
    }



}
