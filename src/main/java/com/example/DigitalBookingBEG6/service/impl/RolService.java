package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.mappers.GenericModelMapper;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Reserva;
import com.example.DigitalBookingBEG6.model.Rol;
import com.example.DigitalBookingBEG6.model.dto.RolDTO;
import com.example.DigitalBookingBEG6.repository.RolRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService implements BaseService<RolDTO> {
    private final RolRepository rolRepository;
    @Autowired
    private GenericModelMapper genericModelMapper;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public RolDTO save(RolDTO element) {
        if(rolRepository.findByRolNombre(element.getNombre()) != null){
            throw new BusinessException("BL-700", "El rol ya existe", HttpStatus.CONFLICT);
        }
        Rol rol = rolRepository.save(genericModelMapper.mapToRol(element));
        return  genericModelMapper.mapToRolDTO(rol);
    }

    @Override
    public List<RolDTO> getAll() {
        List<Rol> listadoRoles = rolRepository.findAll();
        if(listadoRoles.isEmpty()){
            throw new ResourceNotFoundException("NF-700", "No hay roles registrados en la base de datos");
        }
        return listadoRoles.stream().map(e -> genericModelMapper.mapToRolDTO(e)).collect(Collectors.toList());
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
    public RolDTO modify(Integer id, RolDTO element) {
        return null;
    }

    @Override
    public RolDTO getById(Integer id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-701", "No existe el rol con ID " + id));
        return genericModelMapper.mapToRolDTO(rol);
    }

    public RolDTO getByNombre(String nombre){
        Rol rol = rolRepository.findByRolNombre(nombre).orElseThrow(() -> new ResourceNotFoundException("NF-701", "No existe el rol con nombre " + nombre));
        return genericModelMapper.mapToRolDTO(rol);
    }
}
