package com.fantasystocks.controller;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

    @RequestMapping("/hello")
    @ResponseBody
    String helloWorld() {
        return getHelloWorld();
    }

    @VisibleForTesting
    static String getHelloWorld() {
        return "Hello World";
    }
}