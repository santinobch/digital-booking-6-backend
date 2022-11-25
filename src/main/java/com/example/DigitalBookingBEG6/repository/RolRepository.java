package com.example.DigitalBookingBEG6.repository;


import com.example.DigitalBookingBEG6.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByRolNombre(String rolNombre);
}
