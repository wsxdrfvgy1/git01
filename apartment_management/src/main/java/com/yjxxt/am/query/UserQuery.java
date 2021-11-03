package com.yjxxt.am.query;

import com.yjxxt.am.base.BaseQuery;

public class UserQuery extends BaseQuery {
    private String userName;
    public UserQuery() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    @Override
    public String toString() {
        return "UserQuery{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
