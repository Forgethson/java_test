package com.wjd.mybatisplus.service.impl;

import com.wjd.mybatisplus.mapper.UserMapper;
import com.wjd.mybatisplus.pojo.User;
import com.wjd.mybatisplus.service.UserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Date:2022/2/15
 * Author:ybc
 * Description:
 */
@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
