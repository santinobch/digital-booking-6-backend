package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.service.impl.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaController {
    @Autowired
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/categoria/new")
    public ResponseEntity<Categoria> nuevo(@RequestBody Categoria categoria){
        try {
            return ResponseEntity.ok(service.save(categoria));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<?> borrar(@PathVariable (value = "id", required = true) Integer id){
        ResponseEntity<?> response = null;
        try {
            if(service.delete(id)){
                response = ResponseEntity.status(HttpStatus.OK).body("Deleted");
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id", required = true) Integer id) {
        ResponseEntity<?> response = null;
        try {
            response = ResponseEntity.ok(service.getById(id));
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
