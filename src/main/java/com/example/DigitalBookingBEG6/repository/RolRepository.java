package com.example.DigitalBookingBEG6.repository;


import com.example.DigitalBookingBEG6.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(String rolNombre);
}
