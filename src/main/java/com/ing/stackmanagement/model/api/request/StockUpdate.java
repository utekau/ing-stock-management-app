package com.ing.stackmanagement.model.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class StockUpdate {

    @NotNull
    private BigDecimal price;
}
