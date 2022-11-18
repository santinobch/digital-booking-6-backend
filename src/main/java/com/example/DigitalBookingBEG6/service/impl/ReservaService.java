package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
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
        Usuario user = usuarioService.getById(element.getUsuario().getId());
        Producto producto = productoService.getById(element.getProducto().getId());

        // TODO: Confirmar como hacer las validaciones y corregir
        //if(user || producto){
          //  return reservaRepository.save(element);
       // }
        return reservaRepository.save(element);
    }

    public List<Reserva> findByIdProducto(Integer id) {
        Producto producto = productoService.getById(id);
        List listadoReservas = reservaRepository.findByProducto(producto);
        if(listadoReservas.isEmpty()){
            throw new ResourceNotFoundException("NF-302", "No existen reservas correspondientes al producto "+id);
        }
        return reservaRepository.findByProducto(producto);
    }

    @Override
    public List<Reserva> getAll() {
        List<Reserva> reservasEncontradas = reservaRepository.findAll();
        if(reservasEncontradas.isEmpty()){
            throw new ResourceNotFoundException("NF-300", "No hay reservas registradas en la base de datos");
        }
        return reservasEncontradas;
    }

    @Override
    public boolean delete(Integer id) {return false;}

    @Override
    public Reserva modify(Integer id, Reserva element) {
        return null;
    }

    @Override
    public Reserva getById(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-301", "No existe la reserva con ID " + id));
    }
}
