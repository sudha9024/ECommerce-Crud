package com.example.SpringEcom.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("hello")
    public String greet(){
        return "Welcome on the page";
    }
}
