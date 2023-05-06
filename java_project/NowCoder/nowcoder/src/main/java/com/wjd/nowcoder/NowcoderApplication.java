package com.wjd.nowcoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class NowcoderApplication {

    @PostConstruct
    public void init() {
        // 解决netty启动冲突问题
        // 见Netty4Utils.setAvailableProcessors()源码
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(NowcoderApplication.class, args);
    }

}
