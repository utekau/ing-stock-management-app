package com.ing.stackmanagement.model;

import com.ing.stackmanagement.model.entity.StockEntity;
import lombok.Data;

import java.util.Set;

@Data
public class StockExchange {

    private Long id;
    private String name;
    private String description;
    private Set<Stock> includedStocks;
    private Boolean liveInMarket;
}
