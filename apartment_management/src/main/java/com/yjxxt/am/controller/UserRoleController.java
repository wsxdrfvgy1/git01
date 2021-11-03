package com.yjxxt.am.controller;


import com.yjxxt.am.base.BaseController;
import com.yjxxt.am.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user_role")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;
    /*  *//**
     * 房间报表收集
     */
    @RequestMapping("/findType")
    @ResponseBody
    public List<Map<String,Object>> findType(){
        return userRoleService.findRoleType();
    }




}
