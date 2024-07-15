package ru.maxima.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/new-hello-world")
    public String newSayHello(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname) {
        System.out.println("You are inside your controller");
        System.out.println(name.length() + " : " + surname);
        return "hello";
    }


    @GetMapping("/old-hello-world")
    public String oldSayHello(HttpServletRequest request,
                              Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Integer age = Integer.valueOf(request.getParameter("age"));

        model.addAttribute("message", "Hello," + name + ", " + surname);
        model.addAttribute("age", "He is " + age + " years old");

        System.out.println(name + " : " + surname);
        System.out.println("YOu are inside your controller");
        return "hello";
    }





    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello";
    }


    @GetMapping("/point")
    public String privetupir() {
        return "privet upir";
    }
}
