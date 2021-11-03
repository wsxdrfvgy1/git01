package com.yjxxt.am.controller;


import com.yjxxt.am.base.BaseController;
import com.yjxxt.am.service.RoomService;
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
@RequestMapping("/room")
public class RoomController extends BaseController {
    @Autowired
    private RoomService roomService;
    /*
     * 跳转到房间收集页面Room
     */
    @RequestMapping("/toRooom")
    public String a(){
        return "count/analysis";
    }
    /*  *//**
     * 房间报表收集
     */
    @RequestMapping("/findType")
    @ResponseBody
    public List<Map<String,Object>> findType(){
    return roomService.findRoomType();
    }


}
