package com.example.DigitalBookingBEG6.model.dto;

import com.example.DigitalBookingBEG6.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private Integer id;
    private LocalTime hora;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private Producto producto;
    private String nombre;
    private String apellido;
    private String email;
}
