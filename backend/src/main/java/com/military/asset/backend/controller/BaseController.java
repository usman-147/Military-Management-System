package com.military.asset.backend.controller;

import com.military.asset.backend.entity.Base;
import com.military.asset.backend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bases")
@CrossOrigin(origins = "*")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @GetMapping
    public List<Base> getAllBases() {
        return baseService.getAllBases();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Base> getBaseByName(@PathVariable String name) {
        Optional<Base> base = baseService.getBaseByName(name);
        return base.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<Base> getBaseByLocation(@PathVariable String location) {
        Optional<Base> base = baseService.getBaseByLocation(location);
        return base.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Base> createBase(@RequestBody Base base) {
        try {
            Base saved = baseService.createBase(base);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBaseById(@PathVariable Long id) {
        baseService.deleteBaseById(id);
        return ResponseEntity.ok("Deleted base with ID: " + id);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteBaseByName(@PathVariable String name) {
        baseService.deleteByName(name);
        return ResponseEntity.ok("Deleted base with Name: " + name);
    }

    @DeleteMapping("/location/{location}")
    public ResponseEntity<String> deleteBaseByLocation(@PathVariable String location) {
        baseService.deleteByLocation(location);
        return ResponseEntity.ok("Deleted base with Location: " + location);
    }
}
