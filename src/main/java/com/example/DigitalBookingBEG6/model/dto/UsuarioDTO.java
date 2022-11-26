package com.example.DigitalBookingBEG6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String id;
    private String username;
    private String nombre;
    private String apellido;
    private String email;
    private String ciudad;
    private String rol;
}
