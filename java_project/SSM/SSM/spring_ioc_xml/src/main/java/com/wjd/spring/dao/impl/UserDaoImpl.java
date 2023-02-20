package com.wjd.spring.dao.impl;

import com.wjd.spring.dao.UserDao;

/**
 * Date:2022/7/2
 * Author:ybc
 * Description:
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void saveUser() {
        System.out.println("保存成功");
    }
}
