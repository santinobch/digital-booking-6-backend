package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.service.impl.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/new")
    public ResponseEntity<Categoria> nuevo(@RequestBody Categoria categoria){
        try {
            return ResponseEntity.ok(service.save(categoria));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable (value = "id", required = true) Integer id){
        ResponseEntity<?> response = null;
        try {
            if(service.delete(id)){
                response = ResponseEntity.status(HttpStatus.OK).body("Deleted");
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la categoría con ID "+id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id", required = true) Integer id) {
        ResponseEntity<?> response = null;
        try {
            Optional<Categoria> categoria = service.getById(id);
            if(categoria.isPresent()){
                response = ResponseEntity.ok(categoria.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la categoría con ID " + id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable (value = "id", required = true) Integer id, @RequestBody Categoria categoria){
        ResponseEntity<?> response = null;
        try {
            Categoria request = service.modify(id, categoria);
            if (request.getId() == null){
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la categoría con ID " + id);
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
