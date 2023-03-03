package com.wjd.order;

import com.wjd.feign.clients.UserClient;
import com.wjd.feign.config.FeignClientConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@MapperScan("com.wjd.order.mapper")
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FeignClientConfiguration.class, clients = UserClient.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    // 创建RestTemplate并注入Spring容器
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public IRule randomRule() {
//        return new RandomRule();
//    }
}

//@MapperScan("cn.itcast.order.mapper")
//@SpringBootApplication
//@EnableFeignClients(clients = UserClient.class,defaultConfiguration = DefaultFeignConfiguration.class)
//public class OrderApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(OrderApplication.class, args);
//    }
//

//
//   /* @Bean
//    public IRule randomRule() {
//        return new RandomRule();
//    }*/
//}