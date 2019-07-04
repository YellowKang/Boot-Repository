package com.kang.boot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.kang.boot.common",
        "com.kang.boot.test"
})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }

}
