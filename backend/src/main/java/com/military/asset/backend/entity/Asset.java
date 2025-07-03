package com.military.asset.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., "Rifle", "Tank", "Ammunition"
    private String name; // e.g., "AK-47", "T-90", "5.56mm"
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_id")
    private Base base;

    public Asset() {}

    public Asset(String type, String name, int quantity, Base base) {
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.base = base;
    }

    public Long getId() { return id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Base getBase() { return base; }
    public void setBase(Base base) { this.base = base; }
}
