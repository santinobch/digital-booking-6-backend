package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.model.dto.CiudadDTO;
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
@RequestMapping("/cities")
public class CiudadController {
    @Autowired
    private final CiudadService service;

    public CiudadController(CiudadService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<CiudadDTO>> getAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @RequestBody CiudadDTO ciudadDTO){
        return ResponseEntity.ok(service.modify(id, ciudadDTO));
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CiudadDTO ciudadDTO){
        return ResponseEntity.ok(service.save(ciudadDTO));
    }
}
