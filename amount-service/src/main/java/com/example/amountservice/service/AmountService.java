package com.example.amountservice.service;


import com.example.amountservice.dto.AmountResponse;
import com.example.amountservice.repository.AmountRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AmountService {

    private final AmountRepository amountRepository;

    @Transactional(readOnly = true)
    public List<AmountResponse> isInStock(List<String> skuCode){
        log.info("wait Started");

        log.info("wait Ended");
        return amountRepository.findBySkuCodeIn(skuCode)
                .stream().map(amount ->
                    AmountResponse.builder()
                            .isInStock(amount.getNumber()>0)
                            .skuCode(amount.getSkuCode()).build()
                ).toList();
    }
}
