package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.dto.ReservaDTO;
import com.example.DigitalBookingBEG6.service.impl.ProductoService;
import com.example.DigitalBookingBEG6.service.impl.ReservaService;
import com.example.DigitalBookingBEG6.service.impl.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bookings")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService, UsuarioService usuarioService, ProductoService productoService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody ReservaDTO reservaDTO){
        return ResponseEntity.status(201).body(reservaService.save(reservaDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservaDTO>> getAll(Model model) {
        return ResponseEntity.ok(reservaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(reservaService.getById(id));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getByIdProducto(@PathVariable Integer id){
        return ResponseEntity.ok(reservaService.findByIdProducto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return ResponseEntity.status(204).body(reservaService.delete(id));
    }
}
