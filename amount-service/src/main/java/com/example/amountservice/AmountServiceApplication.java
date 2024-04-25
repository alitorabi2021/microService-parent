package com.example.amountservice;

import com.example.amountservice.mode.Amount;
import com.example.amountservice.repository.AmountRepository;
import org.springframework.aop.TargetSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class AmountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmountServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(AmountRepository amountRepository){
        return args -> {

            Amount amount=new Amount();
            amount.setSkuCode("iphone 14");
            amount.setNumber(120);

            Amount amount1=new Amount();
            amount1.setSkuCode("iphone 15");
            amount1.setNumber(130);

            amountRepository.save(amount);
            amountRepository.save(amount1);

        };

    }
}
