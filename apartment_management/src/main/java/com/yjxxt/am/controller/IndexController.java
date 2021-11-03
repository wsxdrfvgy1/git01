package com.yjxxt.am.controller;

import com.yjxxt.am.base.BaseController;
import com.yjxxt.am.bean.User;
import com.yjxxt.am.service.UserService;
import com.yjxxt.am.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 登录页面
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }


    /**
     * 登录成功返回Ajax后success函数跳转到的main后台界面
     */
    @RequestMapping("main")
    public String main(HttpServletRequest req) {

        return "main";
    }



    @RequestMapping("errors")
    public String  error () {
        return "errors";
    }
}
