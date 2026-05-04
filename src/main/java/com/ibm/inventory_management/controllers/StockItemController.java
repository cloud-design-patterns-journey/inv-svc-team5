package com.ibm.inventory_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.inventory_management.models.StockItem;
import com.ibm.inventory_management.services.query.StockQueryService;

/**
 * Query side: read-only, cached endpoints
 */
@RestController
public class StockItemController {

    private final StockQueryService queryService;

    public StockItemController(StockQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping(path = "/stock-items", produces = "application/json")
    public List<StockItem> listStockItems() {
        return this.queryService.listStockItems();
    }

    @GetMapping(path = "/stock-item/{id}", produces = "application/json")
    public StockItem getStockItem(@PathVariable("id") String id) {
        return this.queryService.getStockItem(id);
    }
}