package com.kang.boot.hanlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.kang.boot.common.config.swagger",
        "com.kang.boot.hanlp"
})
public class BootHanlpApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootHanlpApplication.class,args);
    }
}
