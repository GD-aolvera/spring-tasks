package com.gd.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String handleRequest(){
        return "This is our first Spring app!";
    }
}
