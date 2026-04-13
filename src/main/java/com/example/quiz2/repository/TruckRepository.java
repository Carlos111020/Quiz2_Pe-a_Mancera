package com.example.quiz2.repository;

import com.example.quiz2.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TruckRepository extends JpaRepository<Truck, Long> {
    boolean existsByPlate(String plate);
    boolean existsByDriverId(Long driverId);
    Optional<Truck> findByDriverUsername(String username);
}