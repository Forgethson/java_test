package com.hmdp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;

@SpringBootTest
public class miaoshaTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserServiceImpl userService;

    @Test
    // 在Redis中创建1000个用户的token，并将token保存到txt文件，便于Jmeter测试
    void createTokens() throws IOException {
        String path = "../miaosha.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));

        List<User> list = userService.query().ge("id", 10).le("id", 1009).list();
        for (User user : list) {
            String token = UUID.randomUUID().toString(true);
            bw.write(token);
            bw.newLine();
            UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
            Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                    CopyOptions.create()
                            .setIgnoreNullValue(true)
                            .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
            // 7.3.存储
            String tokenKey = LOGIN_USER_KEY + token;
            stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        }
        bw.close();
        System.out.println("写入成功");

    }


}
