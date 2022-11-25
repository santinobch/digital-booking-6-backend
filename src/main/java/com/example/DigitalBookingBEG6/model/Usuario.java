package com.example.DigitalBookingBEG6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer usuarioId;
    @Column
    @NotEmpty
    private String usuarioUsername;
    @Column
    @NotEmpty
    private String usuarioNombre;
    @Column
    @NotEmpty
    private String usuarioApellido;
    @Column
    @Email
    @NotEmpty
    private String usuarioEmail;
    @Column
    @NotEmpty
    private String usuarioPassword;
    @Column
    private String usuarioCiudad;
    @ManyToOne
    @JoinColumn(name="id_rol")
    @NotNull
    private Rol usuarioRol;

    public Usuario (Integer id){
        this.usuarioId = id;
    }
}
