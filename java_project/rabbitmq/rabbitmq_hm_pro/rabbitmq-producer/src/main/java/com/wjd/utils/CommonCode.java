package com.wjd.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CommonCode {
    ConnectionFactory factory;
    Channel channel;
    Connection connection;

    public Channel initMQ() throws IOException, TimeoutException {
        //1.创建连接工厂
        factory = new ConnectionFactory();
        //2. 设置参数
        factory.setHost("192.168.29.128");//ip  默认值 localhost
        factory.setPort(5672); //端口  默认值 5672
        factory.setUsername("admin");//用户名 默认 guest
        factory.setPassword("123456");//密码 默认值 guest
        //3. 创建连接 Connection
        connection = factory.newConnection();
        //4. 创建Channel
        channel = connection.createChannel();
        return channel;
    }

    public void closeMQ() throws IOException, TimeoutException {
        //7.释放资源
        channel.close();
        connection.close();
    }
}
