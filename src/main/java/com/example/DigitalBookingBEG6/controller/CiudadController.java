package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.service.impl.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/ciudades")
public class CiudadController {
    @Autowired
    private final CiudadService service;

    public CiudadController(CiudadService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ciudad>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id") Integer id) {
        ResponseEntity<?> response;
        try {
            Optional<Ciudad> ciudad = service.getById(id);
            if(ciudad.isPresent()){
                response = ResponseEntity.ok(ciudad.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la ciudad con ID " + id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
}
