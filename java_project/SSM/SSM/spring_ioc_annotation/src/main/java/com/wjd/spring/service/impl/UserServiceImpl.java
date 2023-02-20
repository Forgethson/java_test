package com.wjd.spring.service.impl;

import com.wjd.spring.dao.UserDao;
import com.wjd.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Date:2022/7/2
 * Author:ybc
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    //@Qualifier("userDaoImpl")
    private UserDao userDao;

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
