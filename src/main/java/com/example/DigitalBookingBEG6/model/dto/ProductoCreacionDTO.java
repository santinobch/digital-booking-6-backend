package com.example.DigitalBookingBEG6.model.dto;

import com.example.DigitalBookingBEG6.model.Caracteristica;
import com.example.DigitalBookingBEG6.model.Imagen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCreacionDTO {
    private String titulo;
    private String descripcion;
    private Integer idCiudad;
    private Integer idCategoria;
    private List<Imagen> imagenes;
    private List<Caracteristica> caracteristicas;
    private String houseRulesPolicy;
    private String healthAndSecurityPolicy;
    private String cancellationPolicy;
}
