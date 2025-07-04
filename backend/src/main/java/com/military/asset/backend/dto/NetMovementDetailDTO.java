package com.military.asset.backend.dto;

import java.time.LocalDate;

public class NetMovementDetailDTO {

    private LocalDate date;
    private int totalPurchases;
    private int totalTransfersIn;
    private int totalTransfersOut;

    public NetMovementDetailDTO(LocalDate date, int totalPurchases, int totalTransfersIn, int totalTransfersOut) {
        this.date = date;
        this.totalPurchases = totalPurchases;
        this.totalTransfersIn = totalTransfersIn;
        this.totalTransfersOut = totalTransfersOut;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalPurchases() {
        return totalPurchases;
    }

    public int getTotalTransfersIn() {
        return totalTransfersIn;
    }

    public int getTotalTransfersOut() {
        return totalTransfersOut;
    }
}
