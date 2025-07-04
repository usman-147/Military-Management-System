package com.military.asset.backend.repository.impl;

import com.military.asset.backend.repository.DashboardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getNetMovementDetails(Long baseId, Long assetId, LocalDate start, LocalDate end) {
        String sql = """
                SELECT
                    base_id,
                    asset_id,
                    DATE(purchase_date) AS txn_date,
                    SUM(CASE WHEN type = 'PURCHASE' THEN quantity ELSE 0 END) AS purchases,
                    SUM(CASE WHEN type = 'TRANSFER_IN' THEN quantity ELSE 0 END) AS transfers_in,
                    SUM(CASE WHEN type = 'TRANSFER_OUT' THEN quantity ELSE 0 END) AS transfers_out
                FROM (
                    SELECT base_id, asset_id, quantity, purchase_date AS purchase_date, 'PURCHASE' AS type
                    FROM purchases
                    WHERE base_id = :baseId AND asset_id = :assetId AND purchase_date BETWEEN :start AND :end

                    UNION ALL

                    SELECT to_base_id AS base_id, asset_id, quantity, transfer_date AS purchase_date, 'TRANSFER_IN' AS type
                    FROM transfers
                    WHERE to_base_id = :baseId AND asset_id = :assetId AND transfer_date BETWEEN :start AND :end

                    UNION ALL

                    SELECT from_base_id AS base_id, asset_id, quantity, transfer_date AS purchase_date, 'TRANSFER_OUT' AS type
                    FROM transfers
                    WHERE from_base_id = :baseId AND asset_id = :assetId AND transfer_date BETWEEN :start AND :end
                ) AS combined
                GROUP BY base_id, asset_id, txn_date
                ORDER BY txn_date ASC
                """;

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("baseId", baseId);
        query.setParameter("assetId", assetId);
        query.setParameter("start", start);
        query.setParameter("end", end);

        return query.getResultList();
    }
}
