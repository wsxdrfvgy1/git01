package com.yjxxt.am.mapper;

import com.yjxxt.am.base.BaseMapper;
import com.yjxxt.am.bean.UserRole;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {
    @MapKey("")
    List<Map<String, Object>> findRoleType();

    int countUserRoleNum(Integer userId);

    int deleteUserRoleByUserId(Integer userId);
}