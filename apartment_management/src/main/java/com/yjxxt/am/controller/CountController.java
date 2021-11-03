package com.yjxxt.am.controller;


import com.yjxxt.am.base.BaseController;
import com.yjxxt.am.service.RoleService;
import com.yjxxt.am.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/count")
public class CountController extends BaseController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoleService roleService;
    /*
     * 跳转到房间收集页面
     */
    @RequestMapping("/toRoom")
    public String room(){
        System.out.println("22222");
        return "count/analysis";
    }

    /*
     * 跳转到角色收集页面
     */
    @RequestMapping("/toUserRole")
    public String a(){
        return "count/analysis2";
    }


}
