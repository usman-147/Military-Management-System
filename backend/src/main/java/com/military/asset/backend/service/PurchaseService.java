package com.military.asset.backend.service;

import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.entity.Base;
import com.military.asset.backend.entity.Purchase;
import com.military.asset.backend.repository.AssetRepository;
import com.military.asset.backend.repository.BaseRepository;
import com.military.asset.backend.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import com.military.asset.backend.annotation.Loggable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final AssetRepository assetRepository;
    private final BaseRepository baseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, AssetRepository assetRepository, BaseRepository baseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.assetRepository = assetRepository;
        this.baseRepository = baseRepository;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    public List<Purchase> getPurchasesByAssetId(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found: " + assetId));
        return purchaseRepository.findByAsset(asset);
    }

    public List<Purchase> getPurchasesByBaseId(Long baseId) {
        Base base = baseRepository.findById(baseId)
                .orElseThrow(() -> new RuntimeException("Base not found: " + baseId));
        return purchaseRepository.findByBase(base);
    }

    public List<Purchase> getPurchasesByQuantity(int quantity) {
        return purchaseRepository.findByQuantity(quantity);
    }

    public List<Purchase> getPurchasesByDate(LocalDate date) {
        return purchaseRepository.findByDate(date);
    }

    @Loggable
    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public void deletePurchaseById(Long id) {
        purchaseRepository.deleteById(id);
    }

    public void deleteByAssetId(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found: " + assetId));
        List<Purchase> purchases = purchaseRepository.findByAsset(asset);
        purchaseRepository.deleteAll(purchases);
    }

    public void deleteByBaseId(Long baseId) {
        Base base = baseRepository.findById(baseId)
                .orElseThrow(() -> new RuntimeException("Base not found: " + baseId));
        List<Purchase> purchases = purchaseRepository.findByBase(base);
        purchaseRepository.deleteAll(purchases);
    }

    public void deleteByQuantity(int quantity) {
        List<Purchase> purchases = purchaseRepository.findByQuantity(quantity);
        purchaseRepository.deleteAll(purchases);
    }

    public void deleteByDate(LocalDate date) {
        List<Purchase> purchases = purchaseRepository.findByDate(date);
        purchaseRepository.deleteAll(purchases);
    }
}
