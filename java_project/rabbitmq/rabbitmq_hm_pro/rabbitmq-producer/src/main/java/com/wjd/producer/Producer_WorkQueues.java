package com.wjd.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wjd.utils.CommonCode;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送消息
 */
public class Producer_WorkQueues {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 初始化
        CommonCode commonCode = new CommonCode();
        Channel channel = commonCode.initMQ();
        //5. 创建队列Queue，如果没有一个名字叫hello_world的队列，则会创建该队列，如果有则不会创建
        channel.queueDeclare("work_queues", true, false, false, null);
        //6. 发送消息
        for (int i = 1; i <= 10; i++) {
            String body = i + "hello rabbitmq~~~";
            channel.basicPublish("", "work_queues", null, body.getBytes());
        }
        //7.释放资源
        commonCode.closeMQ();
    }
}
