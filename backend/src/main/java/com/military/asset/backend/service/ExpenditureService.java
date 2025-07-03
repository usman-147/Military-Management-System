package com.military.asset.backend.service;

import com.military.asset.backend.entity.Expenditure;
import com.military.asset.backend.enums.AssignmentType;
import com.military.asset.backend.repository.ExpenditureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;

    public ExpenditureService(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }

    public List<Expenditure> getAllExpenditures() {
        return expenditureRepository.findAll();
    }

    public Optional<Expenditure> getExpenditureById(Long id) {
        return expenditureRepository.findById(id);
    }

    public List<Expenditure> getByAssetId(Long assetId) {
        return expenditureRepository.findByAssetId(assetId);
    }

    public List<Expenditure> getByAssignedTo(String assignedTo) {
        return expenditureRepository.findByAssignedTo(assignedTo);
    }

    public List<Expenditure> getByQuantity(int quantity) {
        return expenditureRepository.findByQuantity(quantity);
    }

    public List<Expenditure> getByType(AssignmentType type) {
        return expenditureRepository.findByType(type);
    }

    public List<Expenditure> getByDate(LocalDate date) {
        return expenditureRepository.findByDate(date);
    }

    public Expenditure createExpenditure(Expenditure expenditure) {
        return expenditureRepository.save(expenditure);
    }

    public void deleteById(Long id) {
        expenditureRepository.deleteById(id);
    }

    public void deleteByAssetId(Long assetId) {
        List<Expenditure> list = expenditureRepository.findByAssetId(assetId);
        expenditureRepository.deleteAll(list);
    }

    public void deleteByAssignedTo(String assignedTo) {
        List<Expenditure> list = expenditureRepository.findByAssignedTo(assignedTo);
        expenditureRepository.deleteAll(list);
    }

    public void deleteByQuantity(int quantity) {
        List<Expenditure> list = expenditureRepository.findByQuantity(quantity);
        expenditureRepository.deleteAll(list);
    }

    public void deleteByType(AssignmentType type) {
        List<Expenditure> list = expenditureRepository.findByType(type);
        expenditureRepository.deleteAll(list);
    }

    public void deleteByDate(LocalDate date) {
        List<Expenditure> list = expenditureRepository.findByDate(date);
        expenditureRepository.deleteAll(list);
    }
}
