package com.military.asset.backend.service;

import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.entity.Base;
import com.military.asset.backend.repository.AssetRepository;
import com.military.asset.backend.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final BaseRepository baseRepository;

    public AssetService(AssetRepository assetRepository, BaseRepository baseRepository) {
        this.assetRepository = assetRepository;
        this.baseRepository = baseRepository;
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    public List<Asset> getAssetsByBaseName(String baseName) {
        Optional<Base> base = baseRepository.findByName(baseName);
        return base.map(assetRepository::findByBase)
                   .orElseThrow(() -> new RuntimeException("Base not found with name: " + baseName));
    }

    public List<Asset> getAssetsByType(String type) {
        return assetRepository.findByType(type);
    }

    public List<Asset> getAssetsByName(String name) {
        return assetRepository.findByName(name);
    }

    public List<Asset> getAssetsByQuantity(int quantity) {
        return assetRepository.findByQuantity(quantity);
    }

    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }

    public List<Asset> findByType(String type) {
        return assetRepository.findByType(type);
    }

    public List<Asset> findByName(String name) {
        return assetRepository.findByName(name);
    }

    public List<Asset> findByQuantity(int quantity) {
        return assetRepository.findByQuantity(quantity);
    }

    public void deleteAll(List<Asset> assets) {
        assetRepository.deleteAll(assets);
    }

    public void deleteByBaseName(String baseName) {
        Base base = baseRepository.findByName(baseName)
                .orElseThrow(() -> new RuntimeException("Base not found: " + baseName));
        List<Asset> assets = assetRepository.findByBase(base);
        assetRepository.deleteAll(assets);
    }
}
