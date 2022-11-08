package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.model.Producto;
import com.example.DigitalBookingBEG6.service.impl.CiudadService;
import com.example.DigitalBookingBEG6.service.impl.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private final ProductoService service;
    @Autowired
    private final CiudadService ciudadService;

    public ProductoController(ProductoService service, CiudadService ciudadService) {
        this.service = service;
        this.ciudadService = ciudadService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producto>> listAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/new")
    public ResponseEntity<Producto> nuevo(@RequestBody Producto producto){
        try {
            return ResponseEntity.ok(service.save(producto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable (value = "id") Integer id){
        ResponseEntity<?> response = null;
        try {
            if(service.delete(id)){
                response = ResponseEntity.status(HttpStatus.OK).body("Deleted");
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con ID "+id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable (value = "id") Integer id) {
        ResponseEntity<?> response = null;
        try {
            Optional<Producto> producto = service.getById(id);
            if(producto.isPresent()){
                response = ResponseEntity.ok(producto.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con ID " + id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
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

    @GetMapping("/ciudad/{id}")
    public ResponseEntity<?> getProductosById(@PathVariable Integer id){
        ResponseEntity<?> response;
        try {
            Optional<Ciudad> ciudad = ciudadService.getById(id);
            if(ciudad.isPresent()){
                List<Producto> productosEncontrados = service.getProductosByIdCiudad(id);
                if(productosEncontrados.size() > 0){
                    response = ResponseEntity.ok(productosEncontrados);
                } else{
                    response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos pertenecientes a la ciudad con ID " + id);
                }
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la ciudad con ID " + id);
            }
        } catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;

    }
}
