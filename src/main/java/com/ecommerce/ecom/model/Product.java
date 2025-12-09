package com.ecommerce.ecom.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String department;
    private String imageUrl;

    //  New field for Top Deals
    private boolean topDeal;

    //  Default no-args constructor (required by JPA)
    public Product() {}

    //  All-args constructor
    public Product(String name, String description, double price, String department, String imageUrl, boolean topDeal) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.department = department;
        this.imageUrl = imageUrl;
        this.topDeal = topDeal;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isTopDeal() { return topDeal; }
    public void setTopDeal(boolean topDeal) { this.topDeal = topDeal; }
}
