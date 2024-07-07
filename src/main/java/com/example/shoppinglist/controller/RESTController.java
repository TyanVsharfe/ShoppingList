package com.example.shoppinglist.controller;

import com.example.shoppinglist.form.ProductForm;
import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class RESTController {
    private final ProductService productService;
    
    public RESTController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/all")
    private Iterable<Product> list() {
        return productService.getAllProducts();
    }
    
    @GetMapping("/{id}")
    private Optional<Product> getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }
    
    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Long id) {
        productService.remove(id);
    }
    
    @PutMapping("/")
    private void update(@RequestBody Product product) {
        productService.save(product);
    }
    
    @PostMapping("/")
    private Product addProduct(@RequestBody ProductForm form) {
        return productService.add(form);
    }
}