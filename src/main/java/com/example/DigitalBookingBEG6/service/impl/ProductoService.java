package com.example.DigitalBookingBEG6.service.impl;

import com.example.DigitalBookingBEG6.exceptions.BadRequestException;
import com.example.DigitalBookingBEG6.exceptions.BusinessException;
import com.example.DigitalBookingBEG6.exceptions.ResourceNotFoundException;
import com.example.DigitalBookingBEG6.mappers.GenericModelMapper;
import com.example.DigitalBookingBEG6.model.Imagen;
import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.model.dto.ProductoCreacionDTO;
import com.example.DigitalBookingBEG6.model.dto.ProductoDTO;
import com.example.DigitalBookingBEG6.model.dto.UsuarioCreacionDTO;
import com.example.DigitalBookingBEG6.model.dto.UsuarioDTO;
import com.example.DigitalBookingBEG6.repository.ProductoRepository;
import com.example.DigitalBookingBEG6.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService implements BaseService<ProductoDTO> {
    private final ProductoRepository productoRepository;

    @Autowired
    private GenericModelMapper genericModelMapper;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoDTO save(ProductoDTO element) {
        Producto producto = productoRepository.save(genericModelMapper.mapToProducto(element));
        return genericModelMapper.mapToProductoDTO(producto);
    }

    @Override
    public List<ProductoDTO> getAll() {
        List<Producto> listadoProductos = productoRepository.randomProducts();
        if(listadoProductos.isEmpty()){
            throw new ResourceNotFoundException("NF-200", "No hay productos registrados en la base de datos");
        }
        return listadoProductos.stream().map(e -> genericModelMapper.mapToProductoDTO(e)).collect(Collectors.toList());
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
    public ProductoDTO modify(Integer id, ProductoDTO element) {
        Optional<Producto> opt = productoRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("NF-201", "No existe el producto con ID " + id);
        }
        return this.save(element);
    }

    @Override
    public ProductoDTO getById(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NF-201", "No existe el producto con ID " + id));
        return genericModelMapper.mapToProductoDTO(producto);
    }

    public ProductoDTO create(ProductoCreacionDTO element){
        if(productoRepository.findByProductoTitulo(element.getTitulo()) != null){
            throw new BusinessException("BL-200", "El titulo del producto ya se encuentra registrado", HttpStatus.CONFLICT);
        }
        Producto producto = genericModelMapper.mapToProductoCreacion(element);
        for(Imagen i : producto.getImagenes()){
            i.setProducto(producto);
        }
        return genericModelMapper.mapToProductoDTO(productoRepository.save(producto));
    }

    public List<Producto> obtener4RandomProductos(){
        return productoRepository.randomProducts();
    }

    public List<ProductoDTO> getProductosByIdCiudad(Integer id_ciudad){
        List<Producto> listadoProductos = productoRepository.getProductosByCiudad(id_ciudad);
        if(listadoProductos.isEmpty()){
            throw new ResourceNotFoundException("NF-202", "No existen productos ubicados en la ciudad con ID "+id_ciudad);
        }
        return listadoProductos.stream().map(e -> genericModelMapper.mapToProductoDTO(e)).collect(Collectors.toList());
    }

    public List<ProductoDTO> getProductosByIdCategoria(Integer id_categoria){
        List<Producto> listadoProductos = productoRepository.getProductosByCategoria(id_categoria);
        if(listadoProductos.isEmpty()){
            throw new ResourceNotFoundException("NF-203", "No existen productos correspondientes a la categoría con ID "+id_categoria);
        }
        return listadoProductos.stream().map(e -> genericModelMapper.mapToProductoDTO(e)).collect(Collectors.toList());
    }

    public List<ProductoDTO> getProductosBetweenDates(LocalDate fechaInicio, LocalDate fechaFin){
        if(fechaInicio.isAfter(fechaFin)){
            throw new BadRequestException("BR-200","La fecha final debe ser posterior a la fecha inicial");
        }
        List<Producto> listadoProductos = productoRepository.getProductosBetweenDates(fechaInicio, fechaFin);
        if(listadoProductos.isEmpty()){
            throw new ResourceNotFoundException("NF-204", "No existen productos disponibles entre "+fechaInicio+" y "+fechaFin);
        }
        return listadoProductos.stream().map(e -> genericModelMapper.mapToProductoDTO(e)).collect(Collectors.toList());
    }

    public List<ProductoDTO> getProductosByCityAndBetweenDates(Integer id_ciudad, LocalDate fechaInicio, LocalDate fechaFin){
        List<Producto> listadoProductos = productoRepository.getProductosByCityAndBetweenDates(id_ciudad, fechaInicio, fechaFin);
        if(listadoProductos.isEmpty()){
            throw new ResourceNotFoundException("NF-205", "No existen productos disponibles en la ciudad con ID "+id_ciudad+" entre "+fechaInicio+" y "+fechaFin);
        }
        return listadoProductos.stream().map(e -> genericModelMapper.mapToProductoDTO(e)).collect(Collectors.toList());
    }
}
