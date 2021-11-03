package com.yjxxt.am.service;

import com.yjxxt.am.base.BaseService;
import com.yjxxt.am.bean.User;

import com.yjxxt.am.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class RoomService extends BaseService<User,Integer> {
    @Autowired(required = false)
    private RoomMapper roomMapper;

    /*数据报表*/
    public  List<Map<String, Object>> findRoomType() {
        return roomMapper.findRoomType();
    }










}
