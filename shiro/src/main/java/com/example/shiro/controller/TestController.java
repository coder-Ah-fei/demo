package com.example.shiro.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "testshiro")
public class TestController {

//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @RequestMapping(value = "fun1")
//    public String fun1(){
//        return url;
//    }
}
