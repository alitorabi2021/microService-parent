package com.example.demo.controller;

import com.example.demo.dto.RequestProduct;
import com.example.demo.dto.ResponseProduct;
import com.example.demo.model.Product;
import com.example.demo.service.ServiceProduct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ControllerProduct {

    private final ServiceProduct serviceProduct;


   @PostMapping
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public void setProductEntity(@RequestBody RequestProduct productEntity){
        serviceProduct.setProductEntity(productEntity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseProduct> AllProduct(){
    return serviceProduct.getAllProduct();
    }

    @GetMapping("/findProduct")
    public ResponseProduct findByProduct(@RequestParam Long id){
        return serviceProduct.getProductId(id);
    }
}
