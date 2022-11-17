package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.model.Reserva;
import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.repository.ReservaRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements BaseService<Reserva> {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Reserva save(Reserva element) {
        Optional<Usuario> user = usuarioService.getById(element.getUsuario().getId());
        Optional<Producto> producto = productoService.getById(element.getProducto().getId());

        // TODO: Confirmar como hacer las validaciones y corregir
        if(user.isPresent() && producto.isPresent()){
            return reservaRepository.save(element);
        }
        return reservaRepository.save(element);
    }

    public List<Reserva> findByIdProducto(Integer id) throws Exception {
        Optional<Producto> producto = productoService.getById(id);
        if(producto.isPresent()){
            return reservaRepository.findByProducto(producto.get());
        } else {
            throw new Exception("No encontrado");
        }
    }

    @Override
    public List<Reserva> getAll() {
        return reservaRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Reserva modify(Integer id, Reserva element) {
        return null;
    }

    @Override
    public Optional<Reserva> getById(Integer id) {
        return reservaRepository.findById(id);
    }
}
