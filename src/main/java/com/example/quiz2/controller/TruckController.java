package com.example.quiz2.controller;

import com.example.quiz2.dto.truck.TruckRequest;
import com.example.quiz2.dto.truck.TruckResponse;
import com.example.quiz2.service.TruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
@RequiredArgsConstructor
public class TruckController {

    private final TruckService truckService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<TruckResponse> findAll() {
        return truckService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public TruckResponse findById(@PathVariable Long id) {
        return truckService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TruckResponse save(@Valid @RequestBody TruckRequest request) {
        return truckService.save(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public TruckResponse update(@PathVariable Long id, @Valid @RequestBody TruckRequest request) {
        return truckService.update(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        truckService.delete(id);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/my-truck")
    public TruckResponse findMyTruck(Authentication authentication) {
        return truckService.findMyTruck(authentication.getName());
    }
}