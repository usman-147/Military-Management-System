package com.military.asset.backend.controller;

import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin(origins = "*")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public Optional<Asset> getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id);
    }

    @GetMapping("/baseLocation/{baseLocation}")
    public List<Asset> getAssetsByBaseLocation(@PathVariable String baseLocation) {
        System.out.println("Requested base: " + baseLocation);
        return assetService.getAssetsByBaseLocation(baseLocation);

    }

    @GetMapping("/type/{type}")
    public List<Asset> getAssetsByType(@PathVariable String type) {
        return assetService.getAssetsByType(type);
    }

    @GetMapping("/name/{name}")
    public List<Asset> getAssetsByName(@PathVariable String name) {
    return assetService.getAssetsByName(name);
    }

    @GetMapping("/quantity/{quantity}")
    public List<Asset> getAssetsByQuantity(@PathVariable int quantity) {
    return assetService.getAssetsByQuantity(quantity);
    }

    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
    }
}
