package com.ing.stackmanagement.model.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockCreate {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal currentPrice;
}
