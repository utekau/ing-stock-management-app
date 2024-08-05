package com.ing.stackmanagement.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ing.stackmanagement.model.Stock;
import com.ing.stackmanagement.model.StockExchange;
import com.ing.stackmanagement.model.dto.StockExchangeDTO;
import com.ing.stackmanagement.service.StockExchangeService;
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
public class StockExchangeControllerTest {

    @InjectMocks
    private StockExchangeController stockExchangeController;

    @Mock
    private StockExchangeService stockExchangeService;

    @DisplayName("Get stock exchange")
    @Test
    public void getStockExchange() {
        ZonedDateTime now = ZonedDateTime.now();

        Stock stock = Stock.builder()
                .id(1L)
                .currentPrice(BigDecimal.TEN)
                .description("")
                .lastUpdate(now)
                .name("stock")
                .build();

        StockExchange stockExchange = StockExchange.builder()
                .includedStocks(Lists.newArrayList(stock))
                .liveInMarket(false)
                .description("")
                .name("stockExchange")
                .id(99L)
                .build();

        given(stockExchangeService.getStockExchange("stockExchange")).willReturn(stockExchange);

        StockExchangeDTO stockExchangeDTO = stockExchangeController.getStockExchange("stockExchange").getBody();

        assert stockExchangeDTO != null;
        assertThat(stockExchangeDTO.getId()).isEqualTo(stockExchange.getId());
        assertThat(stockExchangeDTO.getName()).isEqualTo(stockExchange.getName());
        assertThat(stockExchangeDTO.getLiveInMarket()).isEqualTo(stockExchange.getLiveInMarket());
        assertThat(stockExchangeDTO.getIncludedStocks().size()).isEqualTo(1);
    }

    @DisplayName("Add stock to stock exchange")
    @Test
    public void addStockToStockExchange() {
        Boolean result = stockExchangeController.addStockToStockExchange("exchange", 1L).getBody();
        verify(stockExchangeService, times(1)).addStockToStockExchange("exchange", 1L);
        assertThat(result).isEqualTo(true);
    }

    @DisplayName("Remove stock from stock exchange")
    @Test
    public void removeStockFromStockExchange() {
        Boolean result = stockExchangeController.removeStockFromStockExchange("exchange", 1L).getBody();
        verify(stockExchangeService, times(1)).removeStockFromStockExchange("exchange", 1L);
        assertThat(result).isEqualTo(true);
    }
}
