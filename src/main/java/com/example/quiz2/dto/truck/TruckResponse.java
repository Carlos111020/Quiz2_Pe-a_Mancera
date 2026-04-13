package com.example.quiz2.dto.truck;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TruckResponse {

    private Long id;
    private String brand;
    private Double capacity;
    private String color;
    private String plate;
    private Long driverId;
    private String driverUsername;
}