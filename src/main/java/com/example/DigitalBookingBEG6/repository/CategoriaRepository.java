package com.example.DigitalBookingBEG6.repository;

import com.example.DigitalBookingBEG6.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByCategoriaTitulo(String categoriaTitulo);
}
