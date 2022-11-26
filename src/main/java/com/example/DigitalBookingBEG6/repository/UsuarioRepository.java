package com.example.DigitalBookingBEG6.repository;

import com.example.DigitalBookingBEG6.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsuarioEmail(String usuarioEmail);
    Optional<Usuario> findByUsername(String username);
}
