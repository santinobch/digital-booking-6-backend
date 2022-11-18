package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Imagen;
import com.example.DigitalBookingBEG6.service.impl.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImagenController {
    @Autowired
    private final ImagenService service;

    public ImagenController(ImagenService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Imagen>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }
}
