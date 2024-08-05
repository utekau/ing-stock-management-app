package com.ing.stackmanagement.service;

import com.ing.stackmanagement.model.Stock;
import com.ing.stackmanagement.model.api.request.StockCreate;
import com.ing.stackmanagement.model.api.request.StockUpdate;
import com.ing.stackmanagement.model.entity.StockEntity;
import com.ing.stackmanagement.repository.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    @DisplayName("Create stock")
    @Test
    public void createStock() {
        ZonedDateTime now = ZonedDateTime.now();
        StockCreate stockCreate = StockCreate.builder()
                .name("name")
                .currentPrice(BigDecimal.TEN)
                .description("")
                .build();

        Stock stock = stockService.createStock(stockCreate);
        verify(stockRepository, times(1)).save(any());
        assertThat(stock.getLastUpdate()).isAfter(now);
        assertThat(stock.getName()).isEqualTo("name");
        assertThat(stock.getCurrentPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(stock.getDescription()).isEqualTo("");
    }

    @DisplayName("Delete stock")
    @Test
    public void deleteStock() {
        StockEntity mockStock = mock(StockEntity.class);
        when(stockRepository.findById(99L)).thenReturn(Optional.ofNullable(mockStock));

        stockService.deleteStock(99L);
        verify(stockRepository, times(1)).delete(any());
    }

    @DisplayName("Update stock price")
    @Test
    public void updateStockPrice() {
        ZonedDateTime now = ZonedDateTime.now();
        StockEntity stockEntity = new StockEntity();
        stockEntity.setCurrentPrice(BigDecimal.ONE);
        stockEntity.setLastUpdate(now);

        StockUpdate stockUpdate = new StockUpdate(BigDecimal.TEN);
        when(stockRepository.findById(99L)).thenReturn(Optional.of(stockEntity));

        Stock stockUpdated = stockService.updateStockPrice(99L, stockUpdate);
        verify(stockRepository, times(1)).save(any());
        assertThat(stockUpdated.getCurrentPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(stockUpdated.getLastUpdate()).isAfter(now);
    }
}
