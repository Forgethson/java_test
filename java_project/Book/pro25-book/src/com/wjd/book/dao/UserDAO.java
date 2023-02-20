package com.wjd.book.dao;

import com.wjd.book.pojo.User;

public interface UserDAO {
    User getUser(String uname , String pwd );
}
