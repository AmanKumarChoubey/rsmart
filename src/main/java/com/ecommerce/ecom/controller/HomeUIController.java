package com.ecommerce.ecom.controller;

import com.ecommerce.ecom.model.Product;
import com.ecommerce.ecom.service.ProductCsvLoader;
import com.ecommerce.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
//
//@Controller
//public class HomeUIController {
//    private final ProductService productService;
//
//    public HomeUIController(ProductService productService) {
//        this.productService = productService;
//    }
//
//
//    @GetMapping("/")
//    public String home(Model model){
//        List<Product> products = productService.getAllProducts();
//        List<Product> topDeals = productService.getTopDeals();
//
//        model.addAttribute("products", products);
//        model.addAttribute("topDeals", topDeals);
//
//        return "home"; // will load home.html
//    }
//}

@Controller
public class HomeUIController {

    @Autowired
    private  ProductService productService;

//    public HomeUIController(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/")
    public String home(Model model) {
        // get top deals
        List<Product> topDeals = productService.getTopDeals();
        // get all products
        List<Product> products = productService.getAllProducts();
        model.addAttribute("topDeals", topDeals);
        model.addAttribute("products", products);
        return "home"; // home.html
    }
}

