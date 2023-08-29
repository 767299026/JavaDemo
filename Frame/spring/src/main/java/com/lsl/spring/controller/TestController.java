package com.lsl.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/")
    public String turnToHome() {
        return "index";
    }

    @GetMapping("/test")
    public String turnToTest() {
        return "test";
    }
}
