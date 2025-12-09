//package com.ecommerce.ecom.service;
//
//import com.ecommerce.ecom.model.Product;
//import com.ecommerce.ecom.repository.ProductRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductService {
//    private final ProductRepository productRepository;
//
//    public ProductService(ProductRepository productRepository){
//        this.productRepository=productRepository;
//    }
//    public List<Product> getAllProducts(){
//        return productRepository.findAll();
//    }
//    public Product getProductById(Long id){
//        return productRepository.findById(id).orElseThrow(()->new
//                RuntimeException("Product not found with id "+id));
//    }
//    public Product saveProduct(Product product){
//        return productRepository.save(product);
//    }
//    public void deleteProduct(Long id){
//        productRepository.deleteById(id);
//    }
//    public Product updateProduct(Long id,Product product){
//        Product existing = productRepository.findById(id).orElseThrow(()->
//                new RuntimeException("Product not found with id "+id));
//                existing.setName(product.getName());
//                existing.setDescription(product.getDescription());
//                existing.setPrice(product.getPrice());
//                return productRepository.save(existing);
//    }
//
////    add function for top deals
//public List<Product> getTopDeals() {
//    return productRepository.findByTopDealTrue();
//}
//}


package com.ecommerce.ecom.service;

import com.ecommerce.ecom.model.Product;
import com.ecommerce.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> getTopDeals() {
        return repo.findByTopDealTrue();
    }

    // 🔹 Add this method
    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    // 🔹 Optional: to save multiple products at once
    public List<Product> saveAllProducts(List<Product> products) {
        return repo.saveAll(products);
    }
}


