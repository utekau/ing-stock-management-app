package com.ing.stackmanagement.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class StockDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal currentPrice;
    private ZonedDateTime lastUpdate;
}
