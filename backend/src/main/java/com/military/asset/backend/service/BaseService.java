package com.military.asset.backend.service;

import com.military.asset.backend.entity.Base;
import com.military.asset.backend.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaseService {

    @Autowired
    private BaseRepository baseRepository;

    public List<Base> getAllBases() {
        return baseRepository.findAll();
    }

    public Optional<Base> getBaseByName(String name) {
        return baseRepository.findByName(name);
    }

    public Optional<Base> getBaseByLocation(String location) {
        return baseRepository.findByLocation(location);
    }

    public boolean existsByName(String name) {
        return baseRepository.existsByName(name);
    }

    public boolean existsByLocation(String location) {
        return baseRepository.existsByLocation(location);
    }

    public Base createBase(Base base) {
        if (!baseRepository.existsByName(base.getName())) {
            return baseRepository.save(base);
        } else {
            throw new RuntimeException("Base with name already exists: " + base.getName());
        }
    }

    public void deleteBaseById(Long id) {
        baseRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        Optional<Base> base = baseRepository.findByName(name);
        base.ifPresent(baseRepository::delete);
    }

    public void deleteByLocation(String location) {
        Optional<Base> base = baseRepository.findByLocation(location);
        base.ifPresent(baseRepository::delete);
    }

    public void deleteAllBases() {
        baseRepository.deleteAll();
    }
}
