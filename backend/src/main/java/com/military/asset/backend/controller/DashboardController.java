package com.military.asset.backend.controller;

import com.military.asset.backend.dto.DashboardStatsDTO;
import com.military.asset.backend.service.DashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.military.asset.backend.dto.NetMovementDetailDTO;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public DashboardStatsDTO getStats(
            @RequestParam Long baseId,
            @RequestParam Long assetId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return dashboardService.calculateStats(baseId, assetId, startDate, endDate);
    }

    @GetMapping("/net-movement/details")
    public List<NetMovementDetailDTO> getNetMovementBreakdown(
            @RequestParam Long baseId,
            @RequestParam Long assetId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return dashboardService.getNetMovementDetails(baseId, assetId, startDate, endDate);
    }
}
