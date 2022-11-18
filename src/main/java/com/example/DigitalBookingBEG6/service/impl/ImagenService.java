package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.model.Imagen;
import com.example.DigitalBookingBEG6.repository.ImagenRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenService implements BaseService<Imagen> {
    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Override
    public Imagen save(Imagen element) {
        return imagenRepository.save(element);
    }

    @Override
    public List<Imagen> getAll() {
        List<Imagen> imagenesEncontradas = imagenRepository.findAll();
        if(imagenesEncontradas.isEmpty()){
            throw new ResourceNotFoundException("NF-500", "No hay imagenes registradas en la base de datos");
        }
        return imagenesEncontradas;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Imagen> opt = imagenRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("NF-501", "No existe la imagen con ID " + id);
        }
        imagenRepository.deleteById(id);
        return true;
    }

    @Override
    public Imagen modify(Integer id, Imagen element) {
        Optional<Imagen> opt = imagenRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-501", "No existe la imagen con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public Imagen getById(Integer id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-501", "No existe la imagen con ID " + id));
    }
}
