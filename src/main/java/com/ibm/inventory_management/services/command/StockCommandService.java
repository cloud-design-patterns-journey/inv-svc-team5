package com.ibm.inventory_management.services.command;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.ibm.inventory_management.models.StockEvent;
import com.ibm.inventory_management.services.StockItemService;

/**
 * Command side: Write operations, publishes events
 */
@Service
public class StockCommandService {

    private final StockItemService stockItemService;
    private final List<StockEvent> eventLog = new ArrayList<>();

    public StockCommandService(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @CacheEvict(value = {"stockItems", "stockItem"}, allEntries = true)
    public void addStockItem(String name, String manufacturer, double price, int stock) {
        stockItemService.addStockItem(name, manufacturer, price, stock);
        publishEvent(new StockEvent("", StockEvent.EventType.ADD,
                String.format("{name:%s, manufacturer:%s, price:%s, stock:%s}", name, manufacturer, price, stock)));
    }

    @CacheEvict(value = {"stockItems", "stockItem"}, allEntries = true)
    public void updateStockItem(String id, String name, String manufacturer, double price, int stock) {
        stockItemService.updateStockItem(id, name, manufacturer, price, stock);
        publishEvent(new StockEvent(id, StockEvent.EventType.UPDATE,
                String.format("{name:%s, manufacturer:%s, price:%s, stock:%s}", name, manufacturer, price, stock)));
    }

    @CacheEvict(value = {"stockItems", "stockItem"}, allEntries = true)
    public void deleteStockItem(String id) {
        stockItemService.deleteStockItem(id);
        publishEvent(new StockEvent(id, StockEvent.EventType.DELETE, "{}"));
    }

    /**
     * Audit trail: all events published
     */
    private void publishEvent(StockEvent event) {
        eventLog.add(event);
        System.out.println("[EVENT] " + event);
    }

    public List<StockEvent> getEventLog() {
        return new ArrayList<>(eventLog);
    }
}
