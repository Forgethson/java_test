package com.wjd.book.service.impl;

import com.wjd.book.dao.UserDAO;
import com.wjd.book.pojo.User;
import com.wjd.book.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO ;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }
}
