package com.ing.stackmanagement.service;

import com.google.common.collect.Lists;
import com.ing.stackmanagement.model.StockExchange;
import com.ing.stackmanagement.model.entity.StockEntity;
import com.ing.stackmanagement.model.entity.StockExchangeEntity;
import com.ing.stackmanagement.repository.StockExchangeRepository;
import com.ing.stackmanagement.repository.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockExchangeServiceTest {

    @InjectMocks
    private StockExchangeService stockExchangeService;

    @Mock
    private StockExchangeRepository stockExchangeRepository;

    @Mock
    private StockRepository stockRepository;

    @DisplayName("Get stock exchange as live in market false")
    @Test
    public void getStockExchangeNotLiveInMarket() {
        StockExchangeEntity stockExchangeEntity = mock(StockExchangeEntity.class);
        StockEntity mockStock= mock(StockEntity.class);
        when(stockExchangeEntity.getIncludedStocks()).thenReturn(Lists.newArrayList(mockStock));

        given(stockExchangeRepository.findByName("stockExchange")).willReturn(Optional.of(stockExchangeEntity));

        StockExchange stockExchange = stockExchangeService.getStockExchange("stockExchange");
        assertThat(stockExchange.getLiveInMarket()).isEqualTo(false);
    }

    @DisplayName("Get stock exchange as live in market true")
    @Test
    public void getStockExchangeLiveInMarket() {
        StockExchangeEntity stockExchangeEntity = mock(StockExchangeEntity.class);
        StockEntity mockStock1= mock(StockEntity.class);
        StockEntity mockStock2= mock(StockEntity.class);
        StockEntity mockStock3= mock(StockEntity.class);
        StockEntity mockStock4= mock(StockEntity.class);
        StockEntity mockStock5= mock(StockEntity.class);

        when(stockExchangeEntity.getIncludedStocks()).thenReturn(
            Lists.newArrayList(mockStock1, mockStock2, mockStock3,mockStock4, mockStock5)
        );

        given(stockExchangeRepository.findByName("stockExchange")).willReturn(Optional.of(stockExchangeEntity));

        StockExchange stockExchange = stockExchangeService.getStockExchange("stockExchange");
        assertThat(stockExchange.getLiveInMarket()).isEqualTo(true);
    }

    @DisplayName("Add stock to stock exchange")
    @Test
    public void addStockToStockExchange() {
        StockExchangeEntity mockStockExchangeEntity = mock(StockExchangeEntity.class);
        StockEntity mockStock = mock(StockEntity.class);

        when(mockStockExchangeEntity.getIncludedStocks()).thenReturn(Lists.newArrayList());
        given(stockExchangeRepository.findByName("stockExchange")).willReturn(Optional.of(mockStockExchangeEntity));
        given(stockRepository.findById(99L)).willReturn(Optional.of(mockStock));

        stockExchangeService.addStockToStockExchange("stockExchange", 99L);

        assertThat(mockStockExchangeEntity.getIncludedStocks().size()).isEqualTo(1);
        verify(stockExchangeRepository, times(1)).save(any(StockExchangeEntity.class));
    }

    @DisplayName("Remove stock from stock exchange")
    @Test
    public void removeStockFromStockExchange() {
        StockExchangeEntity mockStockExchangeEntity = mock(StockExchangeEntity.class);
        StockEntity mockStock = mock(StockEntity.class);

        when(mockStockExchangeEntity.getIncludedStocks()).thenReturn(Lists.newArrayList(mockStock));
        given(stockExchangeRepository.findByName("stockExchange")).willReturn(Optional.of(mockStockExchangeEntity));
        given(stockRepository.findById(99L)).willReturn(Optional.of(mockStock));

        stockExchangeService.removeStockFromStockExchange("stockExchange", 99L);

        assertThat(mockStockExchangeEntity.getIncludedStocks().size()).isEqualTo(0);
        verify(stockExchangeRepository, times(1)).save(any(StockExchangeEntity.class));
    }
}
