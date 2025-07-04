package com.military.asset.backend.service;

import com.military.asset.backend.dto.DashboardStatsDTO;
import com.military.asset.backend.repository.*;
import org.springframework.stereotype.Service;
import com.military.asset.backend.dto.NetMovementDetailDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private final PurchaseRepository purchaseRepository;
    private final TransferRepository transferRepository;
    private final AssignmentRepository assignmentRepository;
    private final ExpenditureRepository expenditureRepository;
    private final DashboardRepository dashboardRepository;

    public DashboardService(PurchaseRepository purchaseRepository,
                            TransferRepository transferRepository,
                            AssignmentRepository assignmentRepository,
                            ExpenditureRepository expenditureRepository,
                            DashboardRepository dashboardRepository) {
        this.purchaseRepository = purchaseRepository;
        this.transferRepository = transferRepository;
        this.assignmentRepository = assignmentRepository;
        this.expenditureRepository = expenditureRepository;
        this.dashboardRepository = dashboardRepository;
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

    public List<NetMovementDetailDTO> getNetMovementDetails(Long baseId, Long assetId, LocalDate start, LocalDate end) {
        List<Object[]> rawData = dashboardRepository.getNetMovementDetails(baseId, assetId, start, end);
        List<NetMovementDetailDTO> result = new ArrayList<>();

        for (Object[] row : rawData) {
            LocalDate date = (LocalDate) row[2];
            int purchases = ((Number) row[3]).intValue();
            int transfersIn = ((Number) row[4]).intValue();
            int transfersOut = ((Number) row[5]).intValue();
            result.add(new NetMovementDetailDTO(date, purchases, transfersIn, transfersOut));
        }

        return result;
    }
}
