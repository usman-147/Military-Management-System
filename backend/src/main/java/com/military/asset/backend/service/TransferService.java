package com.military.asset.backend.service;

import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.entity.Base;
import com.military.asset.backend.entity.Transfer;
import com.military.asset.backend.repository.AssetRepository;
import com.military.asset.backend.repository.BaseRepository;
import com.military.asset.backend.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AssetRepository assetRepository;
    private final BaseRepository baseRepository;

    public TransferService(TransferRepository transferRepository, AssetRepository assetRepository, BaseRepository baseRepository) {
        this.transferRepository = transferRepository;
        this.assetRepository = assetRepository;
        this.baseRepository = baseRepository;
    }

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public Optional<Transfer> getTransferById(Long id) {
        return transferRepository.findById(id);
    }

    public List<Transfer> getTransfersByAssetId(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found: " + assetId));
        return transferRepository.findByAsset(asset);
    }

    public List<Transfer> getTransfersByFromBaseId(Long fromBaseId) {
        Base fromBase = baseRepository.findById(fromBaseId)
                .orElseThrow(() -> new RuntimeException("Base not found: " + fromBaseId));
        return transferRepository.findByFromBase(fromBase);
    }

    public List<Transfer> getTransfersByToBaseId(Long toBaseId) {
        Base toBase = baseRepository.findById(toBaseId)
                .orElseThrow(() -> new RuntimeException("Base not found: " + toBaseId));
        return transferRepository.findByToBase(toBase);
    }

    public List<Transfer> getTransfersByQuantity(int quantity) {
        return transferRepository.findByQuantity(quantity);
    }

    public List<Transfer> getTransfersBetweenTimestamps(LocalDateTime start, LocalDateTime end) {
        return transferRepository.findByTimestampBetween(start, end);
    }

    public Transfer createTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    public void deleteTransferById(Long id) {
        transferRepository.deleteById(id);
    }

    public void deleteTransfersByAssetId(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found: " + assetId));
        List<Transfer> transfers = transferRepository.findByAsset(asset);
        transferRepository.deleteAll(transfers);
    }

    public void deleteTransfersByFromBaseId(Long fromBaseId) {
        Base base = baseRepository.findById(fromBaseId)
                .orElseThrow(() -> new RuntimeException("Base not found: " + fromBaseId));
        List<Transfer> transfers = transferRepository.findByFromBase(base);
        transferRepository.deleteAll(transfers);
    }

    public void deleteTransfersByToBaseId(Long toBaseId) {
        Base base = baseRepository.findById(toBaseId)
                .orElseThrow(() -> new RuntimeException("Base not found: " + toBaseId));
        List<Transfer> transfers = transferRepository.findByToBase(base);
        transferRepository.deleteAll(transfers);
    }

    public void deleteTransfersByQuantity(int quantity) {
        List<Transfer> transfers = transferRepository.findByQuantity(quantity);
        transferRepository.deleteAll(transfers);
    }

    public void deleteTransfersBetweenTimestamps(LocalDateTime start, LocalDateTime end) {
        List<Transfer> transfers = transferRepository.findByTimestampBetween(start, end);
        transferRepository.deleteAll(transfers);
    }
}
