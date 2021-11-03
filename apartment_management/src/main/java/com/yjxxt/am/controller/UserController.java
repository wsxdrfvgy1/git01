package com.yjxxt.am.controller;

import com.yjxxt.am.base.BaseController;
import com.yjxxt.am.base.ResultInfo;
import com.yjxxt.am.bean.User;
import com.yjxxt.am.model.UserModel;
import com.yjxxt.am.query.UserQuery;
import com.yjxxt.am.service.UserService;
import com.yjxxt.am.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {
        ResultInfo resultInfo = new ResultInfo();
        // 调用Service层的登录方法，得到返回的用户对象
        UserModel userModel = userService.userLogin(userName, userPwd);
        resultInfo.setResult(userModel);
        return resultInfo;
    }


    /**
     * 跳转到修改密码ftl页面
     */
    @RequestMapping("/updatePwd")
    public String updatePwd(){
        return "user/updatePwd";
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updatePword")
    @ResponseBody
    public ResultInfo updatePword( HttpServletRequest req,String oldPassword, String newPassword, String confirmPwd){
        ResultInfo resultInfo = new ResultInfo();
        //获取Cookie中的userId
        int userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //修改密码
        userService.changeUserPwd(userId,oldPassword,newPassword,confirmPwd);
        return resultInfo;
    }



    /**
     * 系统设置-用户管理页面数据
     * 用户查询的中转
     */
    @RequestMapping("index")
    public String index() {
        return "user/user";
    }

    /**
     * 用户管理页面数据
     * 用户查询
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(UserQuery userQuery) {
        return userService.findUserByParams(userQuery);
    }
    /**
     * 用户管理页面数据
     * 用户删除
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        //用户的删除
        userService.removeUserIds(ids);
        //返回目标数据对象
        return success("批量删除用户OK");
    }

    /**
     * 用户管理页面数据
     * 用户添加修改的中转
     */
    @RequestMapping("addOrUpdatePage")
    public String addOrUpdatePage(Integer id, Model model) {
        if(id!=null){
            User user = userService.selectByPrimaryKey(id);
            model.addAttribute("user",user);
        }
        return "user/add_update";
    }

    /**
     * 用户管理页面数据
     * 用户添加
     * @param user
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(User user) {
        //用户的添加
        userService.addUser(user);
        //返回目标数据对象
        return success("用户添加OK");
    }

    /**
     * 用户管理页面数据
     * 用户修改
     * @param user
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(User user) {
        //用户的添加
        userService.changeUser(user);
        //返回目标数据对象
        return success("用户修改OK");
    }

    /*  *//**
     * 数据报表收集
     */
    @RequestMapping("/toana")
    public String a(){
        return "count/analysis";
    }

}
