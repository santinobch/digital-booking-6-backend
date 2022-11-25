package com.example.DigitalBookingBEG6.repository;

import com.example.DigitalBookingBEG6.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
    Ciudad findByCiudadNombreAndCiudadPais(String ciudadNombre, String ciudadPais);
}
