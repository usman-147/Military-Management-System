package com.military.asset.backend.controller;

import com.military.asset.backend.entity.Base;
import com.military.asset.backend.service.BaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bases")
@CrossOrigin(origins = "*")
public class BaseController {

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
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
    public List<Base> getAllBases() {
        return baseService.getAllBases();
    }

    @GetMapping("/{id}")
    public Optional<Base> getBaseById(@PathVariable Long id) {
        return baseService.getBaseById(id);
    }

    @GetMapping("/name/{name}")
    public List<Base> getBasesByName(@PathVariable String name) {
        return baseService.getBasesByName(name);
    }

    @GetMapping("/location/{location}")
    public List<Base> getBasesByLocation(@PathVariable String location) {
        return baseService.getBasesByLocation(location);
    }

    @PostMapping
    public Base createBase(@RequestBody Base base) {
        return baseService.createBase(base);
    }

    @DeleteMapping("/{id}")
    public void deleteBaseById(@PathVariable Long id) {
        baseService.deleteBaseById(id);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        baseService.deleteByName(name);
        return ResponseEntity.ok("Deleted base(s) with name: " + name);
    }

    @DeleteMapping("/location/{location}")
    public ResponseEntity<String> deleteByLocation(@PathVariable String location) {
        baseService.deleteByLocation(location);
        return ResponseEntity.ok("Deleted base(s) with location: " + location);
    }
}
