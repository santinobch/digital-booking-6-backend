package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.model.Reserva;
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
@RequestMapping("/reserves")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService, UsuarioService usuarioService, ProductoService productoService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/")
    public ResponseEntity<?> nuevaReserva(@Valid @RequestBody Reserva reserva){
        return ResponseEntity.status(201).body(reservaService.save(reserva));
    }

    @GetMapping("/")
    public ResponseEntity<List<Reserva>> listAll(Model model) {
        return ResponseEntity.ok(reservaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(reservaService.getById(id));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductosByIdProducto(@PathVariable Integer id){
        return ResponseEntity.ok(reservaService.findByIdProducto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        return ResponseEntity.status(204).body(reservaService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @RequestBody Reserva reserva){
        return ResponseEntity.ok(reservaService.modify(id, reserva));
    }
}
