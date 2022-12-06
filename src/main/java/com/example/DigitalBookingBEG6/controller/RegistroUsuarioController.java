package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.dto.UsuarioCreacionDTO;
import com.example.DigitalBookingBEG6.model.dto.UsuarioDTO;
import com.example.DigitalBookingBEG6.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegistroUsuarioController {
    private final UsuarioService service;

    public RegistroUsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioCreacionDTO usuarioCreacionDTO){
        return ResponseEntity.status(201).body(service.register(usuarioCreacionDTO));
    }
}
