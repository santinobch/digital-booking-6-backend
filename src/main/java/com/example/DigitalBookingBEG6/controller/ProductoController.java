package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.model.dto.ProductoCreacionDTO;
import com.example.DigitalBookingBEG6.model.dto.ProductoDTO;
import com.example.DigitalBookingBEG6.service.impl.CategoriaService;
import com.example.DigitalBookingBEG6.service.impl.CiudadService;
import com.example.DigitalBookingBEG6.service.impl.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductoController {
    @Autowired
    private final ProductoService service;

    public ProductoController(ProductoService service, CiudadService ciudadService, CategoriaService categoriaService) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductoDTO>> getAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<ProductoDTO> create(@Valid @RequestBody ProductoCreacionDTO productoCreacionDTO){
        return ResponseEntity.status(201).body(service.create(productoCreacionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @Valid @RequestBody ProductoDTO productoDTO){
        return ResponseEntity.ok(service.modify(id, productoDTO));
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<?> getProductosByIdCiudad(@PathVariable Integer id){
        return ResponseEntity.ok(service.getProductosByIdCiudad(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductosByIdCategoria(@PathVariable Integer id){
        return ResponseEntity.ok(service.getProductosByIdCategoria(id));
    }

    @GetMapping("/dates")
    public ResponseEntity<?> getProductosBetweenDates(@RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fechaInicio,
                                                      @RequestParam(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin)
    {
        return ResponseEntity.ok(service.getProductosBetweenDates(fechaInicio, fechaFin));
    }

    @GetMapping("/city/{id}/dates")
    public ResponseEntity<?> getProductosByCityAndBetweenDates(@RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fechaInicio,
                                                               @RequestParam(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
                                                               @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(service.getProductosByCityAndBetweenDates(id, fechaInicio, fechaFin));
    }
}
