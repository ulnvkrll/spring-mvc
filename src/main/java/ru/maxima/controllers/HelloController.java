package ru.maxima.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello";
    }


    @GetMapping("/point")
    public String privetupir() {
        return "privet upir";
    }
}
