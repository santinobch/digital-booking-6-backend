package com.example.DigitalBookingBEG6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private  Integer id;
    private String titulo;
    private String descripcion;
    private String ciudad;
    private String pais;

}
