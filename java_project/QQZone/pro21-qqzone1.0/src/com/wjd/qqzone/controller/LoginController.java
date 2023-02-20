package com.wjd.qqzone.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2023/1/24
 */
public class LoginController {
    public String index() {
        // 启动渲染
        return "login";
    }
}
