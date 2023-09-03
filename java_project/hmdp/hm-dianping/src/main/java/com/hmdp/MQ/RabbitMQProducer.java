package com.hmdp.MQ;

import com.alibaba.fastjson.JSONObject;
import com.hmdp.entity.VoucherOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Map;

import static com.hmdp.config.RabbitMQConfig.EXCHANGE_NAME;

@Component
public class RabbitMQProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void seckillSend(VoucherOrder voucherOrder) {
        this.rabbitTemplate.convertAndSend(EXCHANGE_NAME, "seckill", JSONObject.toJSONString(voucherOrder));
    }

//    public void cacheSend(Long voucherOrderId) {
//        this.rabbitTemplate.convertAndSend(EXCHANGE_NAME, "seckill", voucherOrderId.toString());
//    }

    public void sendTest(Map<String, Object> message) {
        this.rabbitTemplate.convertAndSend(EXCHANGE_NAME, "seckill", message);
    }
}
