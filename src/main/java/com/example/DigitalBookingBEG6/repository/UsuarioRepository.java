package com.example.DigitalBookingBEG6.repository;

import com.example.DigitalBookingBEG6.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);
    Usuario findByUsername(String username);
}
