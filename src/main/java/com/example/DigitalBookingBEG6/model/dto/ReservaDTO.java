package com.example.DigitalBookingBEG6.model.dto;

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
    private Integer idReserva;
    private Integer idProducto;
    private String username;
    private LocalTime hora;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
