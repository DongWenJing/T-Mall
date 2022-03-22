package com.tmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tmall.mapper")
public class TMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(TMallApplication.class, args);
    }
}
