package com.military.asset.backend.entity;

import com.military.asset.backend.enums.AssignmentType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenditures")
public class Expenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_id", nullable = false)
    private Base base;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    private String assignedTo;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private AssignmentType type;

    private LocalDate date;

    public Expenditure() {}

    public Expenditure(Base base, Asset asset, String assignedTo, int quantity, AssignmentType type, LocalDate date) {
        this.base = base;
        this.asset = asset;
        this.assignedTo = assignedTo;
        this.quantity = quantity;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AssignmentType getType() {
        return type;
    }

    public void setType(AssignmentType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
