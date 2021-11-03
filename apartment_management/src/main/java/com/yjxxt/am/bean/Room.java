package com.yjxxt.am.bean;

public class Room {
    private Integer id;

    private String roomSize;

    private Integer liveNumber;

    private Integer isClean;

    private Integer isFix;

    private Integer emptyNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize == null ? null : roomSize.trim();
    }

    public Integer getLiveNumber() {
        return liveNumber;
    }

    public void setLiveNumber(Integer liveNumber) {
        this.liveNumber = liveNumber;
    }

    public Integer getIsClean() {
        return isClean;
    }

    public void setIsClean(Integer isClean) {
        this.isClean = isClean;
    }

    public Integer getIsFix() {
        return isFix;
    }

    public void setIsFix(Integer isFix) {
        this.isFix = isFix;
    }

    public Integer getEmptyNumber() {
        return emptyNumber;
    }

    public void setEmptyNumber(Integer emptyNumber) {
        this.emptyNumber = emptyNumber;
    }
}