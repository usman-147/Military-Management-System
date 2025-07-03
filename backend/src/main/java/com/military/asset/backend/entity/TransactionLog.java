package com.military.asset.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_logs")
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // PURCHASE, TRANSFER, ASSIGN, EXPEND
    private Long assetId;
    private Long baseId;
    private int quantity;
    private String username;
    private LocalDateTime timestamp;

    public TransactionLog() {}

    public TransactionLog(String action, Long assetId, Long baseId, int quantity, String username, LocalDateTime timestamp) {
        this.action = action;
        this.assetId = assetId;
        this.baseId = baseId;
        this.quantity = quantity;
        this.username = username;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
