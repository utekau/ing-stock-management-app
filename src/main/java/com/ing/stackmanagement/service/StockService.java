package com.ing.stackmanagement.service;

import com.ing.stackmanagement.exception.ResourceNotFoundException;
import com.ing.stackmanagement.mapper.StockMapper;
import com.ing.stackmanagement.model.Stock;
import com.ing.stackmanagement.model.api.request.StockCreate;
import com.ing.stackmanagement.model.api.request.StockUpdate;
import com.ing.stackmanagement.model.entity.StockEntity;
import com.ing.stackmanagement.model.entity.StockExchangeEntity;
import com.ing.stackmanagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper = StockMapper.INSTANCE;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock createStock(StockCreate stockCreate) {
        StockEntity stockEntity = stockMapper.stockCreateToEntity(stockCreate);
        stockEntity.setLastUpdate(ZonedDateTime.now());
        stockRepository.save(stockEntity);
        return stockMapper.entityToStock(stockEntity);
    }

    public void deleteStock(Long stockId) {
        StockEntity stockEntity = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found " + stockId));
        for (StockExchangeEntity stockExchange: stockEntity.getLinkedStockExchanges()) {
            stockExchange.getIncludedStocks().remove(stockEntity);
        }
        stockRepository.delete(stockEntity);
    }

    public Stock updateStockPrice(Long stockId, StockUpdate stockUpdate) {
        StockEntity stockEntity = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found " + stockId));
        stockEntity.setCurrentPrice(stockUpdate.getPrice());
        stockEntity.setLastUpdate(ZonedDateTime.now());
        stockRepository.save(stockEntity);
        return stockMapper.entityToStock(stockEntity);
    }
}
