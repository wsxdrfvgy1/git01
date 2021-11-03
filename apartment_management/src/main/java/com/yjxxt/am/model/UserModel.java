package com.yjxxt.am.model;

public class UserModel {
    //private int userId;
    private String userIdStr;
    private String userName;
    private String userPwd;

    public UserModel() {
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /*public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


   /* @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", trueName='" + trueName + '\'' +
                '}';
    }*/

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userIdStr=" + userIdStr +
                ", userName='" + userName + '\'' +
                ", trueName='" + userPwd + '\'' +
                '}';
    }
}
