package com.wjd.book.dao.impl;

import com.wjd.book.dao.UserDAO;
import com.wjd.book.pojo.User;
import com.wjd.myssm.basedao.BaseDAO;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return load("select * from t_user where uname like ? and pwd like ? " , uname , pwd );
    }
}
