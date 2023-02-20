package com.wjd.admin.service.impl;

import com.wjd.admin.bean.User;
import com.wjd.admin.mapper.UserMapper;
import com.wjd.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


}
