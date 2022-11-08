package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.service.impl.CategoriaService;
import com.example.DigitalBookingBEG6.service.impl.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductoController {
    @Autowired
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/producto/new")
    public ResponseEntity<Producto> nuevo(@RequestBody Producto producto){
        try {
            return ResponseEntity.ok(service.save(producto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> borrar(@PathVariable (value = "id") Integer id){
        ResponseEntity<?> response = null;
        try {
            if(service.delete(id)){
                response = ResponseEntity.status(HttpStatus.OK).body("Deleted");
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con ID "+id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id") Integer id) {
        ResponseEntity<?> response = null;
        try {
            Optional<Producto> producto = service.getById(id);
            if(producto.isPresent()){
                response = ResponseEntity.ok(producto.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con ID " + id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<?> modify(@PathVariable (value = "id") Integer id, @RequestBody Producto producto){
        ResponseEntity<?> response = null;
        try {
            Producto request = service.modify(id, producto);
            if (request.getId() == null){
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con ID " + id);
            } else {
                response = ResponseEntity.ok(request);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
