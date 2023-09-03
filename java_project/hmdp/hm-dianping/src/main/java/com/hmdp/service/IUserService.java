package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.entity.User;
import org.springframework.web.bind.annotation.CookieValue;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IUserService extends IService<User> {

//    Result sendCode(String phone, HttpSession session);

    Result sendCode(String phone);

    Result login(LoginFormDTO loginForm);

//    Result login(LoginFormDTO loginForm, HttpSession session, String kaptchaOwner);

    Result sign();

    Result signCount();

}
