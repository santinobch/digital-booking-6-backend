package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Rol;
import com.example.DigitalBookingBEG6.service.impl.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/roles")
public class RolController {
    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rol>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id", required = true) Integer id) {
        ResponseEntity<?> response = null;
        try {
            Optional<Rol> rol = service.getById(id);
            if (rol.isPresent()) {
                response = ResponseEntity.ok(rol.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el rol con ID " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PostMapping("/new")
    public ResponseEntity<Rol> nuevo(@RequestBody Rol rol){
        try {
            return ResponseEntity.ok(service.save(rol));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
