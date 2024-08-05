package com.ing.stackmanagement.service;

import com.ing.stackmanagement.exception.ResourceNotFoundException;
import com.ing.stackmanagement.mapper.StockExchangeMapper;
import com.ing.stackmanagement.model.StockExchange;
import com.ing.stackmanagement.model.entity.StockEntity;
import com.ing.stackmanagement.model.entity.StockExchangeEntity;
import com.ing.stackmanagement.repository.StockExchangeRepository;
import com.ing.stackmanagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;
    private final StockRepository stockRepository;
    private final StockExchangeMapper stockExchangeMapper = StockExchangeMapper.INSTANCE;
    private static final int LIVE_IN_MARKET_LIMIT = 5;

    @Autowired
    public StockExchangeService(
        StockExchangeRepository stockExchangeRepository,
        StockRepository stockRepository
    ) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.stockRepository = stockRepository;
    }

    public StockExchange getStockExchange(String stockExchangeName) {
        StockExchangeEntity stockExchangeEntity = stockExchangeRepository.findByName(stockExchangeName)
                .orElseThrow(() -> new ResourceNotFoundException("Stock exchange not found " + stockExchangeName));
        StockExchange stockExchange = stockExchangeMapper.entityToStockExchange(stockExchangeEntity);
        stockExchange.setLiveInMarket(stockExchange.getIncludedStocks().size() >= LIVE_IN_MARKET_LIMIT);
        return stockExchange;
    }

    public void addStockToStockExchange(String stockExchangeName, Long stockId) {
        StockExchangeEntity stockExchangeEntity = stockExchangeRepository.findByName(stockExchangeName)
                .orElseThrow(() -> new ResourceNotFoundException("Stock exchange not found " + stockExchangeName));
        StockEntity stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found " + stockId));
        List<StockEntity> existingStocks = stockExchangeEntity.getIncludedStocks();
        existingStocks.add(stock);
        stockExchangeRepository.save(stockExchangeEntity);
    }

    public void removeStockFromStockExchange(String stockExchangeName, Long stockId) {
        StockExchangeEntity stockExchangeEntity = stockExchangeRepository.findByName(stockExchangeName)
                .orElseThrow(() -> new ResourceNotFoundException("Stock exchange not found " + stockExchangeName));
        StockEntity stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found " + stockId));
        List<StockEntity> existingStocks = stockExchangeEntity.getIncludedStocks();
        existingStocks.remove(stock);
        stockExchangeRepository.save(stockExchangeEntity);
    }
}
