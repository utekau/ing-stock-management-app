package com.ing.stackmanagement.controller;

import com.ing.stackmanagement.mapper.StockMapper;
import com.ing.stackmanagement.model.Stock;
import com.ing.stackmanagement.model.api.request.StockCreate;
import com.ing.stackmanagement.model.api.request.StockUpdate;
import com.ing.stackmanagement.model.dto.StockDTO;
import com.ing.stackmanagement.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;
    private final StockMapper stockMapper = StockMapper.INSTANCE;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Create a new stock.
     *
     * @param stockCreate the product to create
     * @return the ResponseEntity with status 200 (OK) and with body of the new stock
     */
    @PostMapping()
    public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockCreate stockCreate) {
        Stock newStock = stockService.createStock(stockCreate);
        return ResponseEntity.ok(stockMapper.stockToDTO(newStock));
    }

    /**
     * Delete the stock by id.
     *
     * @param stockId
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/{stockId}")
    public ResponseEntity<Boolean> deleteStock(@PathVariable Long stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.ok(true);
    }

    /**
     * Update stock price.
     *
     * @param stockId
     * @param stockUpdate
     * @return the ResponseEntity with status 200 (OK) and with body of the updated stock
     */
    @PutMapping("/{stockId}")
    public ResponseEntity<StockDTO> updateStockPrice(
        @PathVariable Long stockId,
        @Valid @RequestBody StockUpdate stockUpdate
    ) {
        Stock updatedStock = stockService.updateStockPrice(stockId, stockUpdate);
        return ResponseEntity.ok(stockMapper.stockToDTO(updatedStock));
    }
}
