package com.ing.stackmanagement.model.dto;

import com.ing.stackmanagement.model.Stock;
import lombok.Data;

import java.util.Set;

@Data
public class StockExchangeDTO {

    private Long id;
    private String name;
    private String description;
    private Set<Stock> includedStocks;
    private Boolean liveInMarket;
}
