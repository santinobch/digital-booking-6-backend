package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.mappers.GenericModelMapper;
import com.example.DigitalBookingBEG6.model.Caracteristica;
import com.example.DigitalBookingBEG6.model.dto.CaracteristicaDTO;
import com.example.DigitalBookingBEG6.repository.CaracteristicaRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaracteristicaService implements BaseService<CaracteristicaDTO> {
    @Autowired
    private CaracteristicaRepository caracteristicaRepository;
    @Autowired
    private GenericModelMapper genericModelMapper;

    @Override
    public CaracteristicaDTO save(CaracteristicaDTO element) {
        return null;
    }

    @Override
    public List<CaracteristicaDTO> getAll() {
        List<Caracteristica> listadoCaracteristicas = caracteristicaRepository.findAll();
        if(listadoCaracteristicas.isEmpty()){
            throw new ResourceNotFoundException("NF-800", "No hay características registradas en la base de datos");
        }

        return listadoCaracteristicas.stream().map(e -> genericModelMapper.mapToCaracteristicaDTO(e)).collect(Collectors.toList());
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public CaracteristicaDTO modify(Integer id, CaracteristicaDTO element) {
        return null;
    }

    @Override
    public CaracteristicaDTO getById(Integer id) {
        return null;
    }
}
