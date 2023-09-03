package com.hmdp.MQ;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hmdp.entity.User;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.hmdp.service.impl.VoucherOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

import static com.hmdp.config.RabbitMQConfig.SECKILL_CONTAINER_FACTORY_NAME;
import static com.hmdp.config.RabbitMQConfig.SECKILL_QUEUE_NAME;

@Component
public class RabbitMQListener {

    @Resource
    private IVoucherOrderService voucherOrderService;

    /*@RabbitListener(queues = SECKILL_QUEUE_NAME)
    public void seckillListener(VoucherOrder voucherOrder) {
        voucherOrderService.createVoucherOrder(voucherOrder);
    }*/
    @RabbitListener(queues = SECKILL_QUEUE_NAME/*, containerFactory = SECKILL_CONTAINER_FACTORY_NAME*/)
    public void seckillListener(String JSONVoucherOrder) {
        VoucherOrder voucherOrder = JSONObject.parseObject(JSONVoucherOrder, VoucherOrder.class);
//        try {
            voucherOrderService.createVoucherOrder(voucherOrder);
//        } catch (Exception e) {
//            System.out.println("捕获异常：" + e);
//        }
    }
}