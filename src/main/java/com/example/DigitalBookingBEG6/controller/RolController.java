package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Rol;
import com.example.DigitalBookingBEG6.service.impl.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/roles")
public class RolController {
    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Rol>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id", required = true) Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Rol> nuevo(@RequestBody Rol rol){
        return ResponseEntity.status(201).body(service.save(rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }

}
