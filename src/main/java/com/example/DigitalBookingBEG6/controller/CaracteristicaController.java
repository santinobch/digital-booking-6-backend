package com.example.DigitalBookingBEG6.controller;

import com.example.DigitalBookingBEG6.model.dto.CaracteristicaDTO;
import com.example.DigitalBookingBEG6.service.impl.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/features")
public class CaracteristicaController {
    @Autowired
    private final CaracteristicaService service;

    public CaracteristicaController(CaracteristicaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<CaracteristicaDTO>> getAll(Model model) {
        return ResponseEntity.ok(service.getAll());
    }
}
