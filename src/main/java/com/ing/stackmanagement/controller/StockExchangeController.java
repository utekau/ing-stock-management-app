package com.ing.stackmanagement.controller;

import com.ing.stackmanagement.mapper.StockExchangeMapper;
import com.ing.stackmanagement.model.StockExchange;
import com.ing.stackmanagement.model.dto.StockExchangeDTO;
import com.ing.stackmanagement.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;
    private final StockExchangeMapper stockExchangeMapper = StockExchangeMapper.INSTANCE;

    @Autowired
    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    /**
     * Get stock exchange with included stocks.
     *
     * @param stockExchangeName name of the exchange
     * @return the ResponseEntity with status 200 (OK) with stock exchange body
     */
    @GetMapping("/{stockExchangeName}")
    public ResponseEntity<StockExchangeDTO> getStockExchange(@PathVariable String stockExchangeName) {
        StockExchange stockExchange = stockExchangeService.getStockExchange(stockExchangeName);
        return ResponseEntity.ok(stockExchangeMapper.stockExchangeToDTO(stockExchange));
    }

    /**
     * Add stock to stock exchange.
     *
     * @param stockExchangeName name of the exchange
     * @param stockId id of the stock
     * @return the ResponseEntity with status 200 (OK)
     */
    @PutMapping("/{stockExchangeName}/addStock/{stockId}")
    public ResponseEntity<Boolean> addStockToStockExchange(
        @PathVariable String stockExchangeName,
        @PathVariable Long stockId
    ) {
        stockExchangeService.addStockToStockExchange(stockExchangeName, stockId);
        return ResponseEntity.ok(true);
    }

    /**
     * Remove stock from stock exchange.
     *
     * @param stockExchangeName name of the exchange
     * @param stockId id of the stock
     * @return the ResponseEntity with status 200 (OK)
     */
    @PutMapping("/{stockExchangeName}/removeStock/{stockId}")
    public ResponseEntity<Boolean> removeStockFromStockExchange(
            @PathVariable String stockExchangeName,
            @PathVariable Long stockId
    ) {
        stockExchangeService.removeStockFromStockExchange(stockExchangeName, stockId);
        return ResponseEntity.ok(true);
    }
}
