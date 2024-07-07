package com.example.shoppinglist.service;

import com.example.shoppinglist.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppinglist.form.ProductForm;
import com.example.shoppinglist.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product add(ProductForm form) {
        return productRepository.save(new Product(form));
    }
    
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }
    
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public void save(Product product) {
        productRepository.save(product);
    }
}
