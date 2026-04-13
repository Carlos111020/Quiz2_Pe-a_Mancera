package com.example.quiz2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trucks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Double capacity;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false, unique = true)
    private String plate;

    @OneToOne
    @JoinColumn(name = "driver_id", unique = true, nullable = false)
    private AppUser driver;
}