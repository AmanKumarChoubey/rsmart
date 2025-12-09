package com.ecommerce.ecom.service;

import com.ecommerce.ecom.model.Product;
import com.ecommerce.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProductCsvLoader {

    @Autowired
    private ProductService productService;

    @PostConstruct
    public void loadProducts() {
        List<Product> all = new ArrayList<>();

        // load Top Deals (true flag)
        loadCsv("/data/products.csv", true, all);

        // load All Products (false flag)
        loadCsv("/data/more_products.csv", false, all);

        // finally save them
        for (Product p : all) {
            productService.saveProduct(p);
        }
    }

    private void loadCsv(String filePath, boolean topDeal, List<Product> all) {
        try (InputStream is = getClass().getResourceAsStream(filePath)) {
            if (is == null) {
                throw new FileNotFoundException("CSV file not found in resources: " + filePath);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                boolean firstLine = true;
                while ((line = br.readLine()) != null) {
                    if (firstLine) { // skip header
                        firstLine = false;
                        continue;
                    }
                    String[] values = line.split(",");
                    if (values.length < 5) continue;

                    String name = values[0].trim();
                    String description = values[1].trim();
                    double price = Double.parseDouble(values[2].trim());
                    String department = values[3].trim();
                    String imageUrl = values[4].trim();

                    Product p = new Product();
                    p.setName(name);
                    p.setDescription(description);
                    p.setPrice(price);
                    p.setDepartment(department);
                    p.setImageUrl(imageUrl);
                    p.setTopDeal(topDeal);

                    all.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
