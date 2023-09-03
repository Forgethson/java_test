package com.hmdp;

import com.hmdp.entity.User;
import com.hmdp.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
public class SQLTest {

    @Resource
    UserServiceImpl userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(1012L);
        user.setPhone("13133331111");
        user.setNickName("name1");
        boolean save = false;
        try {
            save = userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(save);
    }
}
