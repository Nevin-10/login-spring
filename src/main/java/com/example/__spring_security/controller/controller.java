package com.example.__spring_security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }


    //Add /leaders

    @GetMapping("/leaders")
    public String leadersPage(){
        return "leaders";
    }

    @GetMapping("/systems")
    public String systemsPage(){
        return "systems";
    }







}
