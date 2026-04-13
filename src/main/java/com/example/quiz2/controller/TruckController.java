package com.example.quiz2.controller;

import com.example.quiz2.dto.truck.TruckRequest;
import com.example.quiz2.dto.truck.TruckResponse;
import com.example.quiz2.service.TruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
@RequiredArgsConstructor
public class TruckController {

    private final TruckService truckService;

    @GetMapping
    public List<TruckResponse> findAll() {
        return truckService.findAll();
    }

    @GetMapping("/{id}")
    public TruckResponse findById(@PathVariable Long id) {
        return truckService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TruckResponse save(@Valid @RequestBody TruckRequest request) {
        return truckService.save(request);
    }

    @PutMapping("/{id}")
    public TruckResponse update(@PathVariable Long id, @Valid @RequestBody TruckRequest request) {
        return truckService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        truckService.delete(id);
    }
}