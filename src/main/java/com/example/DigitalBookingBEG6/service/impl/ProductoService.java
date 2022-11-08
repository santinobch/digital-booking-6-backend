package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.repository.ProductoRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements BaseService<Producto> {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto save(Producto element) {
        return productoRepository.save(element);
    }

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        boolean deleted = false;
        try{
            Optional<Producto> opt = productoRepository.findById(id);
            if(opt.isPresent()){
                productoRepository.deleteById(id);
                deleted = true;
            }
        }catch (Exception e){
            throw e;
        }
        return deleted;
    }

    @Override
    public Producto modify(Integer id, Producto element) {
        Producto producto = new Producto();
        try{
            Optional<Producto> opt = productoRepository.findById(id);
            if(opt.isPresent()){
                element.setId(id);
                producto =  this.save(element);
            }
        }catch (Exception e){
            throw e;
        }
        return producto;
    }

    @Override
    public Optional<Producto> getById(Integer id) {
        return productoRepository.findById(id);
    }
}
