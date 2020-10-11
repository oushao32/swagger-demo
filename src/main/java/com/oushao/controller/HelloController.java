package com.oushao.controller;

import com.oushao.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }
    //只要我们的接口中，返回值中存在实体类，他就会被扫描到swagger中
    @ApiOperation("hello控制类")
    @PostMapping(value = "/user")
    public User user() {
        return new User();
    }
}
