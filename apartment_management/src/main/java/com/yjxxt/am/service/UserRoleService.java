package com.yjxxt.am.service;

import com.yjxxt.am.base.BaseService;
import com.yjxxt.am.bean.UserRole;
import com.yjxxt.am.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleService extends BaseService<UserRole,Integer> {


    @Resource
    private UserRoleMapper userRoleMapper;


    public List<Map<String, Object>> findRoleType() {
        return userRoleMapper.findRoleType();
    }
}
