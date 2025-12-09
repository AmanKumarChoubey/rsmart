package com.ecommerce.ecom.repository;

import com.ecommerce.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTopDealTrue(); // 👈 will return only products marked as top deals
}
