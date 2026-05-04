package com.ibm.inventory_management.models;

import java.time.Instant;

public class StockEvent {
    public enum EventType {
        ADD, UPDATE, DELETE, BUY
    }

    private String itemId;
    private EventType eventType;
    private String payload;
    private Instant timestamp;
    private String userId;

    public StockEvent(String itemId, EventType eventType, String payload) {
        this.itemId = itemId;
        this.eventType = eventType;
        this.payload = payload;
        this.timestamp = Instant.now();
        this.userId = System.getProperty("user.name", "system");
    }

    public String getItemId() {
        return itemId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getPayload() {
        return payload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "StockEvent{" +
                "itemId='" + itemId + '\'' +
                ", eventType=" + eventType +
                ", timestamp=" + timestamp +
                ", userId='" + userId + '\'' +
                '}';
    }
}
