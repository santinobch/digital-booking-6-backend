package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.mappers.GenericModelMapper;
import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.model.Reserva;
import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.model.dto.ReservaDTO;
import com.example.DigitalBookingBEG6.model.dto.UsuarioDTO;
import com.example.DigitalBookingBEG6.repository.ReservaRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService implements BaseService<ReservaDTO> {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private GenericModelMapper genericModelMapper;

    @Override
    public ReservaDTO save(ReservaDTO element) {
        usuarioService.findByUsername(element.getUsername());
        productoService.getById(element.getIdProducto());
        Reserva reserva = genericModelMapper.mapToReserva(element);
        reserva.setUsuario(genericModelMapper.mapToUsuario(usuarioService.findByUsername(element.getUsername())));

        return genericModelMapper.mapToReservaDTO(reservaRepository.save(reserva));
    }

    @Override
    public List<ReservaDTO> getAll() {
        List<Reserva> listadoReservas = reservaRepository.findAll();
        if(listadoReservas.isEmpty()){
            throw new ResourceNotFoundException("NF-300", "No hay reservas registradas en la base de datos");
        }
        return listadoReservas.stream().map(e -> genericModelMapper.mapToReservaDTO(e)).collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Reserva> opt = reservaRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("NF-701", "No existe el rol con ID " + id);
        }
        reservaRepository.deleteById(id);
        return true;
    }

    @Override
    public ReservaDTO modify(Integer id, ReservaDTO element) {
        return null;
    }

    @Override
    public ReservaDTO getById(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-301", "No existe la reserva con ID " + id));

        return genericModelMapper.mapToReservaDTO(reserva);
    }

    public List<ReservaDTO> findByIdProducto(Integer id) {
        Producto producto = genericModelMapper.mapToProducto(productoService.getById(id));
        List<Reserva> listadoReservas = reservaRepository.findByProducto(producto);
        if(listadoReservas.isEmpty()){
            throw new ResourceNotFoundException("NF-302", "No existen reservas correspondientes al producto "+id);
        }
        return listadoReservas.stream().map(e -> genericModelMapper.mapToReservaDTO(e)).collect(Collectors.toList());
    }

    public List<ReservaDTO> findByIdUsuario(Integer id){
        Usuario usuario = genericModelMapper.mapToUsuario(usuarioService.getById(id));
        List<Reserva> listadoReservas = reservaRepository.findByUsuario(usuario);
        if(listadoReservas.isEmpty()){
            throw new ResourceNotFoundException("NF-302", "No existen reservas correspondientes al usuario "+usuario.getUsuarioId());
        }
        return listadoReservas.stream().map(e -> genericModelMapper.mapToReservaDTO(e)).collect(Collectors.toList());
    }

}
