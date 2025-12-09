package com.ecommerce.ecom.controller;

import com.ecommerce.ecom.model.User;
import com.ecommerce.ecom.service.UserService;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model, Authentication authentication){
//        Logging-in email
        String email = authentication.getName();
//        Fetch admin details from DB
        User admin = userService.findByEmail(email);

//        Add admin to Thymeleaf model
        model.addAttribute("admin",admin);
        return "admin-dashboard";
    }
}
