package com.example.shoppinglist;

import com.example.shoppinglist.form.ProductForm;
import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class ModuleTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void addProduct(){
        Product savedProduct = new Product(new ProductForm("milk"));
        productService.save(savedProduct);
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals(savedProduct.getName(), "milk");

        Mockito.verify(productRepository, Mockito.times(1)).save(savedProduct);
    }

    @Test
    public void deleteProduct(){
        Optional<Product> product = Optional.of(new Product(new ProductForm("milk")));
        Long id = product.get().getId();
        Mockito.doReturn(product).when(productRepository).findById(id);
        productService.remove(id);
        Assertions.assertNull(product.get().getId());
    }

    @Test
    public void updateProduct(){
        Product savedProduct = new Product(new ProductForm("cucumber"));
        productService.save(savedProduct);

        boolean bought = savedProduct.isBought();
        savedProduct.setBought(!bought);
        productService.save(savedProduct);
        Mockito.doReturn(Optional.of(savedProduct)).when(productRepository).findById(savedProduct.getId());
        productService.getById(savedProduct.getId()).ifPresent(product -> {
            Assertions.assertEquals(product.isBought(), !bought);
        });
    }
}
