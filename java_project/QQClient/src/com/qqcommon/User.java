package com.qqcommon;

import java.io.Serializable;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * 一个用户信息
 * 需要序列化 Serializable 以串行流传输
 */
public class User implements Serializable {

    // 增强兼容性的语句（可不加）
    private static final long serialVersionUID = 1L;

    private String userID;
    private String passwd;

    public User() {
    }


    public User(String userID, String passwd) {
        this.userID = userID;
        this.passwd = passwd;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
