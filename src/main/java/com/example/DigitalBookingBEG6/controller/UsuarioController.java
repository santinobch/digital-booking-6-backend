package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.service.impl.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> nuevo(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(201).body(service.save(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }
}
