package com.military.asset.backend.dto;

public class DashboardStatsDTO {

    private int openingBalance;
    private int closingBalance;
    private int netMovement;
    private int assignedAssets;
    private int expendedAssets;

    public DashboardStatsDTO() {}

    public DashboardStatsDTO(int openingBalance, int closingBalance, int netMovement, int assignedAssets, int expendedAssets) {
        this.openingBalance = openingBalance;
        this.closingBalance = closingBalance;
        this.netMovement = netMovement;
        this.assignedAssets = assignedAssets;
        this.expendedAssets = expendedAssets;
    }

    public int getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(int openingBalance) {
        this.openingBalance = openingBalance;
    }

    public int getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(int closingBalance) {
        this.closingBalance = closingBalance;
    }

    public int getNetMovement() {
        return netMovement;
    }

    public void setNetMovement(int netMovement) {
        this.netMovement = netMovement;
    }

    public int getAssignedAssets() {
        return assignedAssets;
    }

    public void setAssignedAssets(int assignedAssets) {
        this.assignedAssets = assignedAssets;
    }

    public int getExpendedAssets() {
        return expendedAssets;
    }

    public void setExpendedAssets(int expendedAssets) {
        this.expendedAssets = expendedAssets;
    }
}
