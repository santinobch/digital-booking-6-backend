package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.mappers.GenericModelMapper;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.dto.CategoriaDTO;
import com.example.DigitalBookingBEG6.repository.CategoriaRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoriaService implements BaseService<CategoriaDTO> {

    @Autowired
    private final CategoriaRepository categoriaRepository;
    @Autowired
    private GenericModelMapper genericModelMapper;


    @Override
    public CategoriaDTO save(CategoriaDTO element) {
        Categoria categoria = categoriaRepository.save(genericModelMapper.mapToCategoria(element));
        return genericModelMapper.mapToCategoriaDTO(categoria);
    }

    public CategoriaDTO create(CategoriaDTO categoriaDTO){
        if(categoriaRepository.findByCategoriaTitulo(categoriaDTO.getTitulo()) != null){
            throw new BusinessException("BL-600", "La categoría ya se encuentra registrada", HttpStatus.CONFLICT);
        }
        return this.save(categoriaDTO);
    }

    @Override
    public List<CategoriaDTO> getAll() {
        List<Categoria> listadoCategorias = categoriaRepository.findAll();
        if(listadoCategorias.isEmpty()){
            throw new ResourceNotFoundException("NF-600", "No hay categorías registradas en la base de datos");
        }

        return listadoCategorias.stream().map(e -> genericModelMapper.mapToCategoriaDTO(e)).collect(Collectors.toList());
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
    public CategoriaDTO modify(Integer id, CategoriaDTO element) {
        element.setId(id);
        Optional<Categoria> opt = categoriaRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-601", "No existe la categoría con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public CategoriaDTO getById(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-601", "No existe la categoría con ID " + id));
        return genericModelMapper.mapToCategoriaDTO(categoria);
    }
}
