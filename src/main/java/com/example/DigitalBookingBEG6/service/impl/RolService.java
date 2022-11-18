package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Rol;
import com.example.DigitalBookingBEG6.repository.RolRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
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
        boolean deleted = false;
        try{
            Optional<Rol> opt = rolRepository.findById(id);
            if(opt.isPresent()){
                rolRepository.deleteById(id);
                deleted = true;
            }
        }catch (Exception e){
            throw e;
        }
        return deleted;
    }

    @Override
    public Rol modify(Integer id, Rol element) {
        Rol rol = new Rol();
        try{
            Optional<Rol> opt = rolRepository.findById(id);
            if(opt.isPresent()){
                element.setId(id);
                rol =  this.save(element);
            }
        }catch (Exception e){
            throw e;
        }
        return rol;
    }

    @Override
    public Rol getById(Integer id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-701", "No existe el rol con ID " + id));
    }
}
