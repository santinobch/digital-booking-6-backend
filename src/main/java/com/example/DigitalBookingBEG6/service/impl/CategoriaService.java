package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Imagen;
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
        List<Categoria> categoriasEncontradas = categoriaRepository.findAll();
        if(categoriasEncontradas.isEmpty()){
            throw new ResourceNotFoundException("NF-600", "No hay categorías registradas en la base de datos");
        }
        return categoriasEncontradas;
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
    public Categoria getById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-601", "No existe la categoría con ID " + id));
    }
}
