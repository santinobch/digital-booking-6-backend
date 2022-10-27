package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.repository.CategoriaRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements BaseService<Categoria> {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria save(Categoria element) {
        return categoriaRepository.save(element);
    }

    @Override
    public boolean delete(Integer id) {
        boolean deleted = false;
        try{
            Optional<Categoria> opt = categoriaRepository.findById(id);
            if(opt.isPresent()){
                categoriaRepository.deleteById(id);
                deleted = true;
            }
        }catch (Exception e){
            throw e;
        }
        return deleted;
    }

    @Override
    public Categoria modify(Integer id, Categoria element) {
        Categoria categoria = new Categoria();
        try{
            Optional<Categoria> opt = categoriaRepository.findById(id);
            if(opt.isPresent()){
                element.setId(id);
                categoria =  this.save(element);
            }
        }catch (Exception e){
            throw e;
        }
        return categoria;
    }

    @Override
    public Optional<Categoria> getById(Integer id) {
        return categoriaRepository.findById(id);
    }
}
