package ru.maxima.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/new-hello-world")
    public String newSayHello(@RequestParam("name") String name,
                              @RequestParam("surname") String surname) {
        System.out.println("YOu are inside your controller");
        System.out.println(name.length() + " : " + surname);
        return "hello";
    }


    @GetMapping("/old-hello-world")
    public String oldSayHello(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("YOu are inside your controller");
        System.out.println(name.length() + " : " + surname);
        return "hello";
    }
}
