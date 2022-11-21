package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Imagen;
import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.repository.ProductoRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.http.HttpStatus;
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
        if(productoRepository.findByTitulo(element.getTitulo()) != null){
            throw new BusinessException("BL-200", "El titulo del producto ya se encuentra registrado", HttpStatus.CONFLICT);
        }
        return productoRepository.save(element);
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productosEncontrados = productoRepository.randomProducts();
        if(productosEncontrados.isEmpty()){
            throw new ResourceNotFoundException("NF-200", "No hay productos registrados en la base de datos");
        }
        return productosEncontrados;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Producto> opt = productoRepository.findById(id);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("NF-201", "No existe el producto con ID " + id);
        }
        productoRepository.deleteById(id);
        return true;
    }

    @Override
    public Producto modify(Integer id, Producto element) {
        Optional<Producto> opt = productoRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-201", "No existe el producto con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public Producto getById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-201", "No existe el producto con ID " + id));
    }

    public List<Producto> obtener4RandomProductos(){
        return productoRepository.randomProducts();
    }

    public List<Producto> getProductosByIdCiudad(Integer id_ciudad){
        List<Producto> listadoProductos = productoRepository.getProductosByCiudad(id_ciudad);
        if(listadoProductos.isEmpty()){
            throw new ResourceNotFoundException("NF-202", "No existen productos ubicados en la ciudad con ID "+id_ciudad);
        }
        return listadoProductos;
    }

    public List<Producto> getProductosByIdCategoria(Integer id_categoria){
        List listadoProductos = productoRepository.getProductosByCategoria(id_categoria);
        if(listadoProductos.isEmpty()){
            throw new ResourceNotFoundException("NF-203", "No existen productos correspondientes a la categoría con ID "+id_categoria);
        }
        return listadoProductos;
    }
}
