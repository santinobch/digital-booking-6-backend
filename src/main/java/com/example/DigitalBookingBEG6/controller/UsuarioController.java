package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.dto.UsuarioDTO;
import com.example.DigitalBookingBEG6.service.impl.ReservaService;
import com.example.DigitalBookingBEG6.service.impl.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsuarioController {
    private final UsuarioService service;
    private final ReservaService reservaService;

    public UsuarioController(UsuarioService service, ReservaService reservaService) {
        this.service = service;
        this.reservaService = reservaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(service.modify(id, usuarioDTO));
    }

    @GetMapping()
    public ResponseEntity<?> getByUsername(@RequestParam(value = "username") String username){
        return ResponseEntity.ok(service.findByUsername(username));
    }

    @GetMapping("/{id}/bookings")
    public ResponseEntity<?> getBookingsByUsername(@PathVariable Integer id){
        return ResponseEntity.ok(reservaService.findByIdUsuario(id));
    }
}
