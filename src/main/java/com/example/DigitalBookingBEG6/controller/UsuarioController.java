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

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Integer id) {

        return service.getById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<Usuario> nuevo(@Valid @RequestBody Usuario usuario){
        try {
            return ResponseEntity.ok(service.save(usuario));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
