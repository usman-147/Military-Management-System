package com.military.asset.backend.service;

import com.military.asset.backend.dto.DashboardStatsDTO;
import com.military.asset.backend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DashboardService {

    private final PurchaseRepository purchaseRepository;
    private final TransferRepository transferRepository;
    private final AssignmentRepository assignmentRepository;
    private final ExpenditureRepository expenditureRepository;

    public DashboardService(PurchaseRepository purchaseRepository,
                            TransferRepository transferRepository,
                            AssignmentRepository assignmentRepository,
                            ExpenditureRepository expenditureRepository) {
        this.purchaseRepository = purchaseRepository;
        this.transferRepository = transferRepository;
        this.assignmentRepository = assignmentRepository;
        this.expenditureRepository = expenditureRepository;
    }

    public DashboardStatsDTO calculateStats(Long baseId, Long assetId, LocalDate startDate, LocalDate endDate) {
        int purchases = purchaseRepository.sumQuantityByBaseAndAssetAndDate(baseId, assetId, startDate, endDate).orElse(0);
        int transfersIn = transferRepository.sumQuantityByToBaseAndAssetAndDate(baseId, assetId, startDate, endDate).orElse(0);
        int transfersOut = transferRepository.sumQuantityByFromBaseAndAssetAndDate(baseId, assetId, startDate, endDate).orElse(0);
        int assigned = assignmentRepository.sumQuantityByBaseAndAssetAndDate(baseId, assetId, startDate, endDate).orElse(0);
        int expended = expenditureRepository.sumQuantityByBaseAndAssetAndDate(baseId, assetId, startDate, endDate).orElse(0);

        int netMovement = purchases + transfersIn - transfersOut;
        int openingBalance = 0; // Placeholder, will compute in future if time permits
        int closingBalance = openingBalance + netMovement - assigned - expended;

        return new DashboardStatsDTO(openingBalance, closingBalance, netMovement, assigned, expended);
    }
}
