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

    private Long assetId;
    private String assignedTo;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private AssignmentType type; // ASSIGN or EXPEND

    private LocalDateTime date;

    public Assignment() {}

    public Assignment(Long assetId, String assignedTo, int quantity, AssignmentType type, LocalDateTime date) {
        this.assetId = assetId;
        this.assignedTo = assignedTo;
        this.quantity = quantity;
        this.type = type;
        this.date = date;
    }

    public Long getId() { return id; }

    public Long getAssetId() { return assetId; }
    public void setAssetId(Long assetId) { this.assetId = assetId; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public AssignmentType getType() { return type; }
    public void setType(AssignmentType type) { this.type = type; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
