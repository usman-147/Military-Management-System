package com.military.asset.backend.service;

import com.military.asset.backend.entity.Base;
import com.military.asset.backend.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaseService {

    private final BaseRepository baseRepository;

    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<Base> getAllBases() {
        return baseRepository.findAll();
    }

    public Optional<Base> getBaseById(Long id) {
        return baseRepository.findById(id);
    }

    public List<Base> getBasesByName(String name) {
        return baseRepository.findByName(name);
    }

    public List<Base> getBasesByLocation(String location) {
        return baseRepository.findByLocation(location);
    }

    public Base createBase(Base base) {
        return baseRepository.save(base);
    }

    public void deleteBaseById(Long id) {
        baseRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        List<Base> bases = baseRepository.findByName(name);
        baseRepository.deleteAll(bases);
    }

    public void deleteByLocation(String location) {
        List<Base> bases = baseRepository.findByLocation(location);
        baseRepository.deleteAll(bases);
    }
}
