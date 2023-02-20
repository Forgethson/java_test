package com.wjd.mybatisplus;

import com.wjd.mybatisplus.enums.SexEnum;
import com.wjd.mybatisplus.mapper.UserMapper;
import com.wjd.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Date:2022/2/15
 * Author:ybc
 * Description:
 */
@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
//        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result:" + result);
    }

}
