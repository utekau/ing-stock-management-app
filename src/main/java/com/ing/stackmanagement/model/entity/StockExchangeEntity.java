package com.ing.stackmanagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "stock_exchange")
@Getter
@Setter
public class StockExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "stock_exchange_stocks",
            joinColumns = @JoinColumn(name = "stock_exchange_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<StockEntity> includedStocks;
}
