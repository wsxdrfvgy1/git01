package com.yjxxt.am.mapper;

import com.yjxxt.am.base.BaseMapper;
import com.yjxxt.am.bean.Room;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoomMapper extends BaseMapper<Room,Integer> {
    @MapKey("")
    List<Map<String, Object>> findRoomType();
}