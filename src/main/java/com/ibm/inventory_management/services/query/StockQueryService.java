package com.ibm.inventory_management.services.query;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ibm.inventory_management.models.StockItem;
import com.ibm.inventory_management.services.StockItemService;

/**
 * Query side: Read-optimized, cached stock data
 */
@Service
public class StockQueryService {

    private final StockItemService stockItemService;

    public StockQueryService(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @Cacheable(value = "stockItems", key = "'all'")
    public List<StockItem> listStockItems() {
        return stockItemService.listStockItems();
    }

    @Cacheable(value = "stockItem", key = "#id")
    public StockItem getStockItem(String id) {
        return stockItemService.listStockItems().stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
