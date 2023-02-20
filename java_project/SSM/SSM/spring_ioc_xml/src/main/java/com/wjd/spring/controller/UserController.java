package com.wjd.spring.controller;

import com.wjd.spring.service.UserService;

/**
 * Date:2022/7/2
 * Author:ybc
 * Description:
 */
public class UserController {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void saveUser(){
        userService.saveUser();
    }
}
