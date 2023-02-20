package com.wjd.book.controller;

import com.wjd.book.pojo.User;
import com.wjd.book.service.UserService;

public class UserController {

    private UserService userService ;

    public String login(String uname , String pwd ){

        User user = userService.login(uname, pwd);

        System.out.println("user = " + user);
        return "index";
    }
}
