package com.example.demo;

import com.example.demo.dto.RequestProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class Demo15ApplicationTests {

//    @Container
//    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
//            "postgres:15-alpine");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  ProductRepository  productRepository;

    @Autowired
    private ObjectMapper content;

//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry properties){
//        postgres.start();
//         properties.add("spring.datasource.url",postgres::getJdbcUrl);
//    }

    @Test
    void showCreateProduct() throws Exception {
        RequestProduct requestProduct=getRequestProduct();
        String productRequestString=content.writeValueAsString(requestProduct);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/product").contentType(MediaType.APPLICATION_JSON)
                .content(productRequestString)).andExpect(status().isUpgradeRequired());
        Assertions.assertEquals(2,productRepository.findAll().size());

    }

    private RequestProduct getRequestProduct() {
        return RequestProduct
                .builder().name("mnmn ")
                .description("ali by a dffghfghgfh")
                .price(4000).build();
    }

}
