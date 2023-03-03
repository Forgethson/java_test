package com.wjd.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
