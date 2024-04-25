package com.example.amountservice.repository;

import com.example.amountservice.mode.Amount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AmountRepository extends JpaRepository<Amount,Long> {
    List<Amount> findBySkuCodeIn(List<String> name);
}
