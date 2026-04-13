package com.example.quiz2.config;

import com.example.quiz2.entity.*;
import com.example.quiz2.entity.Role;
import com.example.quiz2.repository.AppUserRepository;
import com.example.quiz2.repository.TruckRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AppUserRepository appUserRepository,
                               TruckRepository truckRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            if (appUserRepository.count() == 0) {
                AppUser admin = appUserRepository.save(
                        AppUser.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin123"))
                                .role(Role.ADMIN)
                                .build()
                );

                AppUser driver1 = appUserRepository.save(
                        AppUser.builder()
                                .username("driver1")
                                .password(passwordEncoder.encode("driver123"))
                                .role(Role.DRIVER)
                                .build()
                );

                AppUser driver2 = appUserRepository.save(
                        AppUser.builder()
                                .username("driver2")
                                .password(passwordEncoder.encode("driver123"))
                                .role(Role.DRIVER)
                                .build()
                );

                if (truckRepository.count() == 0) {
                    truckRepository.save(
                            Truck.builder()
                                    .brand("Volvo")
                                    .capacity(18.0)
                                    .color("Blanco")
                                    .plate("ABC123")
                                    .driver(driver1)
                                    .build()
                    );

                    truckRepository.save(
                            Truck.builder()
                                    .brand("Scania")
                                    .capacity(22.0)
                                    .color("Azul")
                                    .plate("XYZ789")
                                    .driver(driver2)
                                    .build()
                    );
                }
            }
        };
    }
}