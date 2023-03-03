package com.wjd.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wjd.utils.CommonCode;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送消息
 */
public class Producer_Topics {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 初始化
        CommonCode commonCode = new CommonCode();
        Channel channel = commonCode.initMQ();
        String exchangeName = "test_topic";
        //5. 创建交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, false, null);
        //6. 创建队列
        String queue1Name = "test_topic_queue1";
        String queue2Name = "test_topic_queue2";
        channel.queueDeclare(queue1Name, true, false, false, null);
        channel.queueDeclare(queue2Name, true, false, false, null);
        //7. 绑定队列和交换机
        // routing key  系统的名称.日志的级别。
        //=需求： 所有error级别的日志存入数据库，所有order系统的日志存入数据库
        channel.queueBind(queue1Name, exchangeName, "#.error");
        channel.queueBind(queue1Name, exchangeName, "order.*");
        channel.queueBind(queue2Name, exchangeName, "*.*");
        //8. 发送消息
//        channel.basicPublish(exchangeName, "goods.error", null, "RoutingKey为：goods.error".getBytes());
//        channel.basicPublish(exchangeName, "xxx.goods.error", null, "RoutingKey为：xxx.goods.error".getBytes());
//        channel.basicPublish(exchangeName, "order.test", null, "RoutingKey为：order.test".getBytes());
//        channel.basicPublish(exchangeName, "order.test.test", null, "RoutingKey为：order.test.test".getBytes());
        channel.basicPublish(exchangeName, "aaa.test", null, "RoutingKey为：aaa.test".getBytes());
        //9. 释放资源
        commonCode.closeMQ();
    }
}
