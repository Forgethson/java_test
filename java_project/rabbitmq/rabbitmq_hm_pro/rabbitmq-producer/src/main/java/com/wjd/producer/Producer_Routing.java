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
public class Producer_Routing {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 初始化
        CommonCode commonCode = new CommonCode();
        Channel channel = commonCode.initMQ();
        String exchangeName = "test_direct";
        //5. 创建交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, false, null);
        //6. 创建队列
        channel.queueDeclare("test_direct_queue1", true, false, false, null);
        channel.queueDeclare("test_direct_queue2", true, false, false, null);
        //队列1绑定 error
        channel.queueBind("test_direct_queue1", exchangeName, "error");
        //队列2绑定 info  error  warning
        channel.queueBind("test_direct_queue2", exchangeName, "info");
        channel.queueBind("test_direct_queue2", exchangeName, "error");
        channel.queueBind("test_direct_queue2", exchangeName, "warning");
//        String body = "日志信息：张三调用了delete方法...出错误了。。。日志级别：error...";
        //8. 发送消息
        channel.basicPublish(exchangeName, "warning", null, "日志级别：warning".getBytes());
        channel.basicPublish(exchangeName, "error", null, "日志级别：error".getBytes());
        //9. 释放资源
        commonCode.closeMQ();
    }
}
