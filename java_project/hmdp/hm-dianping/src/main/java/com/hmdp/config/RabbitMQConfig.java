package com.hmdp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "topic_exchange";
    public static final String SECKILL_QUEUE_NAME = "seckill_topic_queue";
    public static final String CACHE_QUEUE_NAME = "cache_topic_queue";
    public static final String SECKILL_CONTAINER_FACTORY_NAME = "seckillRabbitListenerContainerFactory";

    //1.交换机
    @Bean("topic_exchange")
    public Exchange bootExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    //2.Queue 队列
    @Bean("seckill_Queue")
    public Queue seckillQueue() {
        return QueueBuilder.durable(SECKILL_QUEUE_NAME).build();
    }

    @Bean("cache_Queue")
    public Queue cacheQueue() {
        return QueueBuilder.durable(CACHE_QUEUE_NAME).build();
    }

    //3. 队列和交互机绑定关系 Binding
    @Bean
    public Binding bindQueueExchange1(@Qualifier("seckill_Queue") Queue queue, @Qualifier("topic_exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("seckill").noargs();
    }

//    @Bean
//    public Binding bindQueueExchange2(@Qualifier("cache_Queue") Queue queue, @Qualifier("topic_exchange") Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("cache").noargs();
//    }


    @Bean(SECKILL_CONTAINER_FACTORY_NAME)
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setMessageConverter(new SimpleMessageConverter());
        //确认方式，manual为手动ack.
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //每次处理数据数量，提高并发量
        factory.setPrefetchCount(250);
        //设置消费者并发数
        factory.setConcurrentConsumers(30);
        //最大消费者并行数
        factory.setMaxConcurrentConsumers(50);
        /* setConnectionFactory：设置spring-amqp的ConnectionFactory。 */
        factory.setConnectionFactory(connectionFactory);
        //factory.setDefaultRequeueRejected(true);
        //使用自定义线程池来启动消费者。
//        factory.setTaskExecutor(taskExecutor());

        ArrayBlockingQueue<Runnable> RunnableQueue = new ArrayBlockingQueue<>(100);
        ExecutorService threadPool = new ThreadPoolExecutor(
                30,
                100,
                2L,
                TimeUnit.SECONDS,
                RunnableQueue,
                Executors.defaultThreadFactory(),
                (r, executor) -> RunnableQueue.offer(r)
        );
        factory.setTaskExecutor(threadPool);

        return factory;
    }

    @Bean("correctTaskExecutor")
    @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(30);
        // 设置最大线程数
        executor.setMaxPoolSize(100);
        // 设置队列容量
        executor.setQueueCapacity(100);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(300);
        // 设置默认线程名称
        executor.setThreadNamePrefix("thread-file-queue");
        // 设置拒绝策略rejection-policy：当pool已经达到max size的时候，丢弃
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }


}
