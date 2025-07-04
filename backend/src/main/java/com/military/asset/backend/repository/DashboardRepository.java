package com.military.asset.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface DashboardRepository extends Repository<Object, Long> {

    @Query(value = """
        SELECT
            p.base_id AS baseId,
            p.asset_id AS assetId,
            p.date AS date,
            COALESCE(SUM(p.quantity), 0) AS totalPurchases,
            COALESCE(SUM(ti.quantity), 0) AS totalTransfersIn,
            COALESCE(SUM(tof.quantity), 0) AS totalTransfersOut
        FROM purchases p
        LEFT JOIN transfers ti ON ti.to_base_id = p.base_id AND ti.asset_id = p.asset_id AND ti.date = p.date
        LEFT JOIN transfers tof ON tof.from_base_id = p.base_id AND tof.asset_id = p.asset_id AND tof.date = p.date
        WHERE p.base_id = :baseId AND p.asset_id = :assetId AND p.date BETWEEN :start AND :end
        GROUP BY p.base_id, p.asset_id, p.date
        """, nativeQuery = true)
    List<Object[]> getNetMovementDetails(
        @Param("baseId") Long baseId,
        @Param("assetId") Long assetId,
        @Param("start") LocalDate start,
        @Param("end") LocalDate end
    );
}
