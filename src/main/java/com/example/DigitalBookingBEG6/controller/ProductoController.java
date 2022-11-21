package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.model.Imagen;
import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.service.impl.CategoriaService;
import com.example.DigitalBookingBEG6.service.impl.CiudadService;
import com.example.DigitalBookingBEG6.service.impl.ProductoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductoController {
    @Autowired
    private final ProductoService service;

    public ProductoController(ProductoService service, CiudadService ciudadService, CategoriaService categoriaService) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Producto>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Producto> nuevo(@Valid @RequestBody Producto producto){
        return ResponseEntity.status(201).body(service.save(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @Valid @RequestBody Producto producto){
        return ResponseEntity.ok(service.modify(id, producto));
    }

    @GetMapping("/random")
    public ResponseEntity<List<Producto>> getRandom(){
        return ResponseEntity.ok(service.obtener4RandomProductos());
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<?> getProductosByIdCiudad(@PathVariable Integer id){
        return ResponseEntity.ok(service.getProductosByIdCiudad(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductosByIdCategoria(@PathVariable Integer id){
        return ResponseEntity.ok(service.getProductosByIdCategoria(id));
    }
}
