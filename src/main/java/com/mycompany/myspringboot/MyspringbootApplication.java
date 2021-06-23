package com.mycompany.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mycompany.myspringboot.*.dao")
@EnableCaching
public class MyspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyspringbootApplication.class, args);
    }
}
