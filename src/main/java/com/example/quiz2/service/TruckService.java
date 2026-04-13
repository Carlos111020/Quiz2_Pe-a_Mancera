package com.example.quiz2.service;

import com.example.quiz2.dto.truck.TruckRequest;
import com.example.quiz2.dto.truck.TruckResponse;
import com.example.quiz2.entity.AppUser;
import com.example.quiz2.entity.Role;
import com.example.quiz2.entity.Truck;
import com.example.quiz2.exception.ResourceNotFoundException;
import com.example.quiz2.repository.AppUserRepository;
import com.example.quiz2.repository.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;
    private final AppUserRepository appUserRepository;

    public List<TruckResponse> findAll() {
        return truckRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TruckResponse findById(Long id) {
        Truck truck = truckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Camión no encontrado con id: " + id));
        return toResponse(truck);
    }

    public TruckResponse save(TruckRequest request) {
        if (truckRepository.existsByPlate(request.getPlate())) {
            throw new IllegalArgumentException("Ya existe un camión con esa placa");
        }

        AppUser driver = appUserRepository.findById(request.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver no encontrado con id: " + request.getDriverId()));

        if (driver.getRole() != Role.DRIVER) {
            throw new IllegalArgumentException("El usuario asignado debe tener rol DRIVER");
        }

        if (truckRepository.existsByDriverId(driver.getId())) {
            throw new IllegalArgumentException("Ese conductor ya tiene un camión asignado");
        }

        Truck truck = Truck.builder()
                .brand(request.getBrand())
                .capacity(request.getCapacity())
                .color(request.getColor())
                .plate(request.getPlate())
                .driver(driver)
                .build();

        return toResponse(truckRepository.save(truck));
    }

    public TruckResponse update(Long id, TruckRequest request) {
        Truck truck = truckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Camión no encontrado con id: " + id));

        AppUser driver = appUserRepository.findById(request.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver no encontrado con id: " + request.getDriverId()));

        if (driver.getRole() != Role.DRIVER) {
            throw new IllegalArgumentException("El usuario asignado debe tener rol DRIVER");
        }

        if (!truck.getPlate().equals(request.getPlate()) && truckRepository.existsByPlate(request.getPlate())) {
            throw new IllegalArgumentException("Ya existe otro camión con esa placa");
        }

        if (!truck.getDriver().getId().equals(driver.getId()) && truckRepository.existsByDriverId(driver.getId())) {
            throw new IllegalArgumentException("Ese conductor ya tiene un camión asignado");
        }

        truck.setBrand(request.getBrand());
        truck.setCapacity(request.getCapacity());
        truck.setColor(request.getColor());
        truck.setPlate(request.getPlate());
        truck.setDriver(driver);

        return toResponse(truckRepository.save(truck));
    }

    public void delete(Long id) {
        Truck truck = truckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Camión no encontrado con id: " + id));

        truckRepository.delete(truck);
    }

    public TruckResponse findMyTruck(String username) {
        Truck truck = truckRepository.findByDriverUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No tienes un camión asignado"));
        return toResponse(truck);
    }

    private TruckResponse toResponse(Truck truck) {
        return TruckResponse.builder()
                .id(truck.getId())
                .brand(truck.getBrand())
                .capacity(truck.getCapacity())
                .color(truck.getColor())
                .plate(truck.getPlate())
                .driverId(truck.getDriver().getId())
                .driverUsername(truck.getDriver().getUsername())
                .build();
    }
}