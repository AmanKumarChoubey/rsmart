package com.ecommerce.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/customer/dashboard")
    public String customerDashboard(){
        return "customer-dashboard";
    }
}
