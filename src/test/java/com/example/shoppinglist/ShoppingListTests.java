package com.example.shoppinglist;

import com.example.shoppinglist.controller.RESTController;
import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingListTests {
    @Autowired
    private RESTController restController;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void restTest() throws Exception{
        assertThat(restController).isNotNull();
    }

    @Test
    public void listTest() throws Exception {
        this.mockMvc.perform(get("/api/product/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addTest() throws Exception {
        this.mockMvc.perform(post("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tomato\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
