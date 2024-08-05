package com.ing.stackmanagement.controller;

import com.ing.stackmanagement.model.Stock;
import com.ing.stackmanagement.model.api.request.StockCreate;
import com.ing.stackmanagement.model.api.request.StockUpdate;
import com.ing.stackmanagement.model.dto.StockDTO;
import com.ing.stackmanagement.service.StockService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @InjectMocks
    private StockController stockController;

    @Mock
    private StockService stockService;

    @DisplayName("Create stock")
    @Test
    public void createStock() {
        ZonedDateTime now = ZonedDateTime.now();
        StockCreate stockCreate = StockCreate.builder()
                .name("name")
                .currentPrice(BigDecimal.TEN)
                .description("")
                .build();

        Stock stock = Stock.builder()
                .id(1L)
                .currentPrice(BigDecimal.TEN)
                .description("")
                .lastUpdate(now)
                .name("name")
                .build();

        given(stockService.createStock(stockCreate)).willReturn(stock);

        StockDTO stockDTO = stockController.createStock(stockCreate).getBody();

        assertThat(stockDTO.getId()).isEqualTo(stock.getId());
        assertThat(stockDTO.getName()).isEqualTo(stock.getName());
        assertThat(stockDTO.getLastUpdate()).isEqualTo(stock.getLastUpdate());
        assertThat(stockDTO.getCurrentPrice()).isEqualTo(stock.getCurrentPrice());
    }

    @DisplayName("Delete stock")
    @Test
    public void deleteStock() {
        Boolean result = stockController.deleteStock(1L).getBody();
        verify(stockService, times(1)).deleteStock(1L);
        assertThat(result).isEqualTo(true);
    }

    @DisplayName("Update stock price")
    @Test
    public void updateStockPrice() {
        ZonedDateTime now = ZonedDateTime.now();

        Stock stock = Stock.builder()
                .id(1L)
                .currentPrice(BigDecimal.ONE)
                .description("")
                .lastUpdate(now)
                .name("name")
                .build();

        StockUpdate stockUpdate = new StockUpdate(BigDecimal.ONE);
        given(stockService.updateStockPrice(1L, stockUpdate)).willReturn(stock);

        StockDTO stockDTO = stockController.updateStockPrice(1L, stockUpdate).getBody();

        assert stockDTO != null;
        assertThat(stockDTO.getCurrentPrice()).isEqualTo(stockUpdate.getPrice());
        assertThat(stockDTO.getLastUpdate()).isEqualTo(stock.getLastUpdate());
    }
}
