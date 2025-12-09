package com.ecommerce.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddStoreController {

    @GetMapping("/admin/add-store")
    public String addNewStore(){
        return "add-store";
    }
}
