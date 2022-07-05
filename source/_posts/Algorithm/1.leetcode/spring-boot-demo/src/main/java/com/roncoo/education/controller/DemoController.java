package com.roncoo.education.controller;

import com.pei.spring.autoconfigure.service.Greeter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {
    @Resource(name = "greeter")
    private Greeter greeter;

    @GetMapping("/say")
    public String sayWhat() {
        return greeter.sayHello();
    }

}