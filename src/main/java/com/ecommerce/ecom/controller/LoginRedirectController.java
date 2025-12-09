package com.ecommerce.ecom.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginRedirectController {

    @GetMapping("/redirectBasedOnRole")
    public  String redirect(Authentication authentication){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role =  authentication.getAuthorities().iterator().next().getAuthority();

        switch(role){
            case "ROLE_ADMIN":
                return "redirect:/admin/dashboard";
            case "ROLE_MANAGER":
                return "redirect:/manager/dashboard";
            case "ROLE_SHOPKEEPER":
                return "redirect:/shop/dashboard";
            case "ROLE_CUSTOMER":
                return "redirect:/customer/dashboard";
            default:
                return "redirect:/login";
        }
    }
}
