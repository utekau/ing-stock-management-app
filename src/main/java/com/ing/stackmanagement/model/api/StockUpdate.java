package com.ing.stackmanagement.model.api;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockUpdate {

    @NotNull
    private BigDecimal price;
}
