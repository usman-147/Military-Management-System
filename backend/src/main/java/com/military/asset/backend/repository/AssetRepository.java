package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByBase(Base base);
    List<Asset> findByType(String type);
    List<Asset> findByName(String name);
    List<Asset> findByQuantity(int quantity);
}
