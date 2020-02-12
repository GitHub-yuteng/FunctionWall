package com.functionwall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.functionwall.dao")
public class FunctionwallApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionwallApplication.class, args);
    }
}


