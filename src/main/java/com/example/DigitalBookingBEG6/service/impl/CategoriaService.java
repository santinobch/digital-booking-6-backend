package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.repository.CategoriaRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.http.HttpStatus;
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
        if(categoriaRepository.findByCategoriaTitulo(element.getCategoriaTitulo()) != null){
            throw new BusinessException("BL-600", "La categoría ya se encuentra registrada", HttpStatus.CONFLICT);
        }
        return categoriaRepository.save(element);
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Categoria> opt = categoriaRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("NF-601", "No existe la categoría con ID "+id);
        }
        categoriaRepository.deleteById(id);
        return true;
    }

    @Override
    public Categoria modify(Integer id, Categoria element) {
        Optional<Categoria> opt = categoriaRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-601", "No existe la categoría con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public Categoria getById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-601", "No existe la categoría con ID " + id));
    }
}
