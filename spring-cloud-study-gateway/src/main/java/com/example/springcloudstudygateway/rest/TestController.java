package com.example.springcloudstudygateway.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/demo")
    public String demo(String test){
        return test ;
    }
}
