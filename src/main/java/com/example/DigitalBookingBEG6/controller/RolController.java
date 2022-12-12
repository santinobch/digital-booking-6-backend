package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Reserva;
import com.example.DigitalBookingBEG6.model.Rol;
import com.example.DigitalBookingBEG6.model.dto.RolDTO;
import com.example.DigitalBookingBEG6.service.impl.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<RolDTO>> getAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable (value = "id", required = true) Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<RolDTO> create(@Valid @RequestBody RolDTO rolDTO){
        return ResponseEntity.status(201).body(service.save(rolDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }
}
