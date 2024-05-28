package ru.maxima.springboottest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HelloController {

    @Value("${welcome}")
    private String welcome;

    @GetMapping("/hello")
    public String hello() {
        log.info(welcome);
        return "hello";
    }
}
