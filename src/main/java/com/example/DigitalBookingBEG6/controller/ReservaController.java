package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.model.Reserva;
import com.example.DigitalBookingBEG6.model.Usuario;
import com.example.DigitalBookingBEG6.service.impl.ProductoService;
import com.example.DigitalBookingBEG6.service.impl.ReservaService;
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
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    public ReservaController(ReservaService reservaService, UsuarioService usuarioService, ProductoService productoService) {
        this.reservaService = reservaService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> nuevaReserva(@Valid @RequestBody Reserva reserva){
        try {
            Optional<Usuario> user = usuarioService.getById(reserva.getUsuario().getId());
            Optional<Producto> producto = productoService.getById(reserva.getProducto().getId());
            if(user.isPresent() && producto.isPresent()){
                return ResponseEntity.ok(reservaService.save(reserva));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reserva>> listAll(Model model) {
        return ResponseEntity.ok(reservaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id") Integer id) {
        ResponseEntity<?> response = null;
        try {
            Optional<Reserva> reserva = reservaService.getById(id);
            if(reserva.isPresent()){
                response = ResponseEntity.ok(reserva.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la reserva con ID " + id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> findByProducto(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(reservaService.findByIdProducto(id));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }




}
