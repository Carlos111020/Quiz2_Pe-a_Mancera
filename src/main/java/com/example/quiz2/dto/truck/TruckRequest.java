package com.example.quiz2.dto.truck;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TruckRequest {

    @NotBlank(message = "La marca es obligatoria")
    private String brand;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser mayor a 0")
    private Double capacity;

    @NotBlank(message = "El color es obligatorio")
    private String color;

    @NotBlank(message = "La placa es obligatoria")
    private String plate;

    @NotNull(message = "El driverId es obligatorio")
    private Long driverId;
}