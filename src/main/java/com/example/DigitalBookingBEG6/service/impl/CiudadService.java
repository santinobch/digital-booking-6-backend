package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.mappers.GenericModelMapper;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.model.dto.CiudadDTO;
import com.example.DigitalBookingBEG6.repository.CiudadRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CiudadService implements BaseService<CiudadDTO> {

    private final CiudadRepository ciudadRepository;
    @Autowired
    private GenericModelMapper genericModelMapper;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    @Override
    public CiudadDTO save(CiudadDTO element) {
        if(ciudadRepository.findByCiudadNombreAndCiudadPais(element.getNombre(), element.getPais()) != null){
            throw new BusinessException("BL-400", "La ciudad ya se encuentra registrada", HttpStatus.CONFLICT);
        }
        Ciudad ciudad =ciudadRepository.save(genericModelMapper.mapToCiudad(element));
        return genericModelMapper.mapToCiudadDTO(ciudad);
    }

    @Override
    public List<CiudadDTO> getAll() {
        List<Ciudad> listadoCiudades = ciudadRepository.findAll();
        if(listadoCiudades.isEmpty()){
            throw new ResourceNotFoundException("NF-400", "No hay ciudades registradas en la base de datos");
        }
        return listadoCiudades.stream().map(e -> genericModelMapper.mapToCiudadDTO(e)).collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Ciudad> opt = ciudadRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("NF-401", "No existe la ciudad con ID " + id);
        }
        ciudadRepository.deleteById(id);
        return true;
    }

    @Override
    public CiudadDTO modify(Integer id, CiudadDTO element) {
        Optional<Ciudad> opt = ciudadRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-401", "No existe la ciudad con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public CiudadDTO getById(Integer id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-401", "No existe la ciudad con ID " + id));
        return genericModelMapper.mapToCiudadDTO(ciudad);
    }
}
