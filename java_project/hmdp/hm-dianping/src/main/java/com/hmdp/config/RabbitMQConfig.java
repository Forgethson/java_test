package com.hmdp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "topic_exchange";
    public static final String SECKILL_QUEUE_NAME = "seckill_topic_queue";

    //1.交换机
    @Bean("topic_exchange")
    public Exchange bootExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    //2.Queue 队列
    @Bean("seckill_Queue")
    public Queue bootQueue() {
        return QueueBuilder.durable(SECKILL_QUEUE_NAME).build();
    }

    //3. 队列和交互机绑定关系 Binding
    @Bean
    public Binding bindQueueExchange(@Qualifier("seckill_Queue") Queue queue, @Qualifier("topic_exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("seckill").noargs();
    }

}
