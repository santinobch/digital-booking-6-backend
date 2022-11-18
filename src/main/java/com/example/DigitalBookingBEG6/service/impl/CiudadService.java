package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.repository.CiudadRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService implements BaseService<Ciudad> {

    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    @Override
    public Ciudad save(Ciudad element) {
        return ciudadRepository.save(element);
    }

    @Override
    public List<Ciudad> getAll() {
        List<Ciudad> ciudadesEncontradas = ciudadRepository.findAll();
        if(ciudadesEncontradas.isEmpty()){
            throw new ResourceNotFoundException("NF-400", "No hay ciudades registradas en la base de datos");
        }
        return ciudadesEncontradas;
    }

    @Override
    public boolean delete(Integer id) {
        boolean deleted = false;
        try{
            Optional<Ciudad> opt = ciudadRepository.findById(id);
            if(opt.isPresent()){
                ciudadRepository.deleteById(id);
                deleted = true;
            }
        }catch (Exception e){
            throw e;
        }
        return deleted;
    }

    @Override
    public Ciudad modify(Integer id, Ciudad element) {
        Ciudad ciudad = new Ciudad();
        try{
            Optional<Ciudad> opt = ciudadRepository.findById(id);
            if(opt.isPresent()){
                element.setId(id);
                ciudad =  this.save(element);
            }
        }catch (Exception e){
            throw e;
        }
        return ciudad;
    }

    @Override
    public Ciudad getById(Integer id) {
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-401", "No existe la ciudad con ID " + id));
    }
}
