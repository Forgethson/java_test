package com.wjd.consumer;

import com.rabbitmq.client.*;
import com.wjd.utils.CommonCode;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer_WorkQueues1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 初始化
        CommonCode commonCode = new CommonCode();
        Channel channel = commonCode.initMQ();
        //5. 创建队列Queue，如果没有一个名字叫hello_world的队列，则会创建该队列，如果有则不会创建
        channel.queueDeclare("work_queues", true, false, false, null);

        // 接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body：" + new String(body));
            }
        };
        channel.basicConsume("work_queues", true, consumer);
        //关闭资源？不要
    }
}
