package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseRepository extends JpaRepository<Base, Long> {

    List<Base> findByName(String name);
    List<Base> findByLocation(String location);
    boolean existsByName(String name);
    boolean existsByLocation(String location);
}
