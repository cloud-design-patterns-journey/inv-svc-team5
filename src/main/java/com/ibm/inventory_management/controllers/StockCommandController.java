package com.ibm.inventory_management.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.inventory_management.services.command.StockCommandService;

/**
 * Command side: write operations, publishes events
 */
@RestController
@RequestMapping("/admin")
public class StockCommandController {

    private final StockCommandService commandService;

    public StockCommandController(StockCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping(path = "/stock-item")
    public void addStockItem(@RequestParam String name, @RequestParam String manufacturer, @RequestParam float price,
            @RequestParam int stock) {
        this.commandService.addStockItem(name, manufacturer, price, stock);
    }

    @PutMapping(path = "/stock-item/{id}")
    public void updateStockItem(@PathVariable("id") String id, @RequestParam String name,
            @RequestParam String manufacturer, @RequestParam float price, @RequestParam int stock) {
        this.commandService.updateStockItem(id, name, manufacturer, price, stock);
    }

    @DeleteMapping(path = "/stock-item/{id}")
    public void deleteStockItem(@PathVariable("id") String id) {
        this.commandService.deleteStockItem(id);
    }

    @GetMapping(path = "/events")
    public java.util.List<?> getEventLog() {
        return this.commandService.getEventLog();
    }
}
