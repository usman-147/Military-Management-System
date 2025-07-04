package com.military.asset.backend.entity;

import com.military.asset.backend.enums.AssignmentType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "base_id")
    private Base base;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private AssignmentType type;

    @Column(name = "date")
    private LocalDateTime date;

    public Assignment() {}

    public Assignment(Base base, Asset asset, String assignedTo, Integer quantity, AssignmentType type, LocalDateTime date) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public AssignmentType getType() {
        return type;
    }

    public void setType(AssignmentType type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
