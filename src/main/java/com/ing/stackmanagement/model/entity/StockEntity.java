package com.ing.stackmanagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "stock")
@Getter
@Setter
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private BigDecimal currentPrice;

    @Column(nullable = false)
    private ZonedDateTime lastUpdate;

    @ManyToMany(mappedBy = "includedStocks")
    private List<StockExchangeEntity> linkedStockExchanges;
}
