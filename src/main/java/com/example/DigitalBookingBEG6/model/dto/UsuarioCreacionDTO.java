package com.example.DigitalBookingBEG6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreacionDTO extends UsuarioDTO{
    private String password;
}
