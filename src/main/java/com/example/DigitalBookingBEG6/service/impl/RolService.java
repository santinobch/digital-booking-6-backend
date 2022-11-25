package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Reserva;
import com.example.DigitalBookingBEG6.model.Rol;
import com.example.DigitalBookingBEG6.repository.RolRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements BaseService<Rol> {
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol save(Rol element) {
        if(rolRepository.findByRolNombre(element.getRolNombre()) != null){
            throw new BusinessException("BL-700", "El rol ya existe", HttpStatus.CONFLICT);
        }
        return rolRepository.save(element);
    }

    @Override
    public List<Rol> getAll() {
        List<Rol> rolesEncontradas = rolRepository.findAll();
        if(rolesEncontradas.isEmpty()){
            throw new ResourceNotFoundException("NF-700", "No hay roles registrados en la base de datos");
        }
        return rolesEncontradas;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Rol> opt = rolRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("NF-701", "No existe el rol con ID " + id);
        }
        rolRepository.deleteById(id);
        return true;
    }

    @Override
    public Rol modify(Integer id, Rol element) {
        Optional<Rol> opt = rolRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-701", "No existe el rol con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public Rol getById(Integer id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-701", "No existe el rol con ID " + id));
    }
}
