package com.hmdp;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmdp.MQ.RabbitMQProducer;
import com.hmdp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;

import static com.hmdp.config.RabbitMQConfig.EXCHANGE_NAME;

@SpringBootTest
public class RabbitMQTest {

    @Resource
    private RabbitMQProducer rabbitMQProducer;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "hmdp");
        rabbitMQProducer.sendTest(map);
    }

    @Test
    public void send2() {
        User user = new User();
        user.setNickName("hmdp");
        user.setPhone("123456");
        String userString = JSON.toJSONString(user);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "seckill", userString);
    }
}
