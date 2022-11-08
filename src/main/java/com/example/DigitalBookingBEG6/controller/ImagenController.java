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
@RequestMapping("/imagenes")
public class ImagenController {
    @Autowired
    private final ImagenService service;

    public ImagenController(ImagenService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Imagen>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Integer id) {
        ResponseEntity<?> response;
        try {
            Optional<Imagen> imagen = service.getById(id);
            if(imagen.isPresent()){
                response = ResponseEntity.ok(imagen.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la imagen con ID " + id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
