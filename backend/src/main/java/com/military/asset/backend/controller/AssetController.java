package com.military.asset.backend.controller;

import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.service.AssetService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public Optional<Asset> getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id);
    }

    @GetMapping("/base/{baseName}")
    public List<Asset> getAssetsByBase(@PathVariable String baseName) {
        return assetService.getAssetsByBaseName(baseName);
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

    @DeleteMapping("/type/{type}")
    public ResponseEntity<String> deleteByType(@PathVariable String type) {
        List<Asset> assets = assetService.findByType(type);
        if (assets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        assetService.deleteAll(assets);
        return ResponseEntity.ok("Deleted " + assets.size() + " asset(s) of type: " + type);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        List<Asset> assets = assetService.findByName(name);
        if (assets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        assetService.deleteAll(assets);
        return ResponseEntity.ok("Deleted " + assets.size() + " asset(s) with name: " + name);
    }

    @DeleteMapping("/quantity/{quantity}")
    public ResponseEntity<String> deleteByQuantity(@PathVariable int quantity) {
        List<Asset> assets = assetService.findByQuantity(quantity);
        if (assets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        assetService.deleteAll(assets);
        return ResponseEntity.ok("Deleted " + assets.size() + " asset(s) with quantity: " + quantity);
    }

    @DeleteMapping("/base/{baseName}")
    public ResponseEntity<String> deleteByBase(@PathVariable String baseName) {
        try {
            assetService.deleteByBaseName(baseName);
            return ResponseEntity.ok("Deleted asset(s) from base: " + baseName);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
