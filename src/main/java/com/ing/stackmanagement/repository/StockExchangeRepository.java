package com.ing.stackmanagement.repository;

import com.ing.stackmanagement.model.entity.StockExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockExchangeRepository extends JpaRepository<StockExchangeEntity, Long> {

    Optional<StockExchangeEntity> findByName(String name);
}
