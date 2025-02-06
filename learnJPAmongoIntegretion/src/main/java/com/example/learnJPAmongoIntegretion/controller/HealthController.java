package com.example.learnJPAmongoIntegretion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("/checkHealth")
    public String healthCheck(){
        return "OK!!!!";
    }
}
