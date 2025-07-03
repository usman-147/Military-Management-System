package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseRepository extends JpaRepository<Base, Long> {
    Optional<Base> findByName(String name);
    Optional<Base> findByLocation(String location);
    boolean existsByName(String name);
    boolean existsByLocation(String location);
}
