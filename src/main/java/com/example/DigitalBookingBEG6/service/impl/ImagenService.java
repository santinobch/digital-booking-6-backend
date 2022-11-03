package com.example.DigitalBookingBEG6.service.impl;

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
        return imagenRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        boolean deleted = false;
        try{
            Optional<Imagen> opt = imagenRepository.findById(id);
            if(opt.isPresent()){
                imagenRepository.deleteById(id);
                deleted = true;
            }
        }catch (Exception e){
            throw e;
        }
        return deleted;
    }

    @Override
    public Imagen modify(Integer id, Imagen element) {
        Imagen imagen = new Imagen();
        try{
            Optional<Imagen> opt = imagenRepository.findById(id);
            if(opt.isPresent()){
                element.setId(id);
                imagen =  this.save(element);
            }
        }catch (Exception e){
            throw e;
        }
        return imagen;
    }

    @Override
    public Optional<Imagen> getById(Integer id) {
        return imagenRepository.findById(id);
    }
}
