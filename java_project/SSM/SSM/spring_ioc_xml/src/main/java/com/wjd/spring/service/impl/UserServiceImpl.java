package com.wjd.spring.service.impl;

import com.wjd.spring.dao.UserDao;
import com.wjd.spring.service.UserService;

/**
 * Date:2022/7/2
 * Author:ybc
 * Description:
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
