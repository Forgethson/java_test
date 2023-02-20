package com.wjd.mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjd.mybatisx.pojo.User;
import com.wjd.mybatisx.service.UserService;
import com.wjd.mybatisx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Forgethson
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-02-18 22:20:29
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




