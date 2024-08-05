package com.ing.stackmanagement.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
public class Stock {

    private Long id;
    private String name;
    private String description;
    private BigDecimal currentPrice;
    private ZonedDateTime lastUpdate;
}
