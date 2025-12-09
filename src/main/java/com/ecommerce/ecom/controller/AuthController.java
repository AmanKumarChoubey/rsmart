package com.ecommerce.ecom.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController{
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

//    @GetMapping("register")
//    public String registerPage(){
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(User user){
//        return "redirect:/login?success";
//    }
}