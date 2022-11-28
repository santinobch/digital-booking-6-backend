package com.example.DigitalBookingBEG6.repository;

import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.model.Reserva;
import com.example.DigitalBookingBEG6.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByProducto(Producto producto);

    List<Reserva> findByUsuario(Usuario usuario);
}
