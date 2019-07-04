package com.kang.boot.test.controller;

import com.kang.boot.test.annotation.MethodUpArgs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @MethodUpArgs(argsName = {"test"},argsValue = {"testAs"})
    @GetMapping("test")
    public String test(String test){
        return test;
    }

}

