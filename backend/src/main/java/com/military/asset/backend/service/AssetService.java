package com.military.asset.backend.service;

import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    public List<Asset> getAssetsByBaseLocation(String baseLocation) {
        return assetRepository.findByBaseLocation(baseLocation);
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

    public List<Asset> findByBaseLocation(String baseLocation) {
        return assetRepository.findByBaseLocation(baseLocation);
    }

    public void deleteAll(List<Asset> assets) {
        assetRepository.deleteAll(assets);
    }

}
