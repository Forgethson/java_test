package com.wjd.consumer;

import com.rabbitmq.client.*;
import com.wjd.utils.CommonCode;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer_Routing2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 初始化
        CommonCode commonCode = new CommonCode();
        Channel channel = commonCode.initMQ();
        String queue1Name = "test_direct_queue1";
        // 接收消息
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body："+new String(body));
                System.out.println("将日志信息存储到数据库.....");
            }
        };
        channel.basicConsume(queue1Name,true,consumer);
        //关闭资源？不要
    }
}
