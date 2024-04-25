package com.example.demo.service;

import com.example.demo.dto.RequestProduct;
import com.example.demo.dto.ResponseProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
@Slf4j
public class ServiceProduct {

    private final ProductRepository productRepository;
    public void setProductEntity(RequestProduct requestProduct) {
        Product product=Product.builder()
                .name(requestProduct.getName()).
            description(requestProduct.getDescription())
                .price(requestProduct.getPrice())
                .build();
        productRepository.save(product);
        log.info("create table in factory");
    }

    public ResponseProduct getProductId(Long id){
        Product product=productRepository.getAllById(id);
        ResponseProduct responseProduct=ResponseProduct.builder().id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();
        return responseProduct;
    }

    public List<ResponseProduct> getAllProduct(){
        List<Product> products= productRepository.findAll();
        return products.stream().map(this::mapToResponseProduct).toList();
    }

    private ResponseProduct mapToResponseProduct(Product product) {
        return ResponseProduct.builder()
        .id(product.getId())
          .name(product.getName())
                .description(product
                        .getDescription())
                .price(product.getPrice()).build();


    }


}
