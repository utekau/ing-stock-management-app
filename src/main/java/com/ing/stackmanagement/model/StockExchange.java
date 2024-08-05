package com.ing.stackmanagement.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StockExchange {

    private Long id;
    private String name;
    private String description;
    private List<Stock> includedStocks;
    private Boolean liveInMarket;
}
