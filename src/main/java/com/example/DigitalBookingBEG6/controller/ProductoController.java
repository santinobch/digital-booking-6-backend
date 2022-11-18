package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Categoria;
import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.service.impl.CategoriaService;
import com.example.DigitalBookingBEG6.service.impl.CiudadService;
import com.example.DigitalBookingBEG6.service.impl.ProductoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductoController {
    @Autowired
    private final ProductoService service;
    @Autowired
    private final CiudadService ciudadService;
    @Autowired
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService service, CiudadService ciudadService, CategoriaService categoriaService) {
        this.service = service;
        this.ciudadService = ciudadService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Producto>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Producto> nuevo(@RequestBody Producto producto){
        try {
            return ResponseEntity.ok(service.save(producto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        return ResponseEntity.status(204).body(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable (value = "id") Integer id, @RequestBody Producto producto){
        ResponseEntity<?> response = null;
        try {
            Producto request = service.modify(id, producto);
            if (request.getId() == null){
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con ID " + id);
            } else {
                response = ResponseEntity.ok(request);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/random")
    public ResponseEntity<List<Producto>> getRandom(){
        return ResponseEntity.ok(service.obtener4RandomProductos());
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<?> getProductosByIdCiudad(@PathVariable Integer id){
        return ResponseEntity.ok(service.getProductosByIdCiudad(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductosByIdCategoria(@PathVariable Integer id){
        return ResponseEntity.ok(service.getProductosByIdCategoria(id));
    }
}
