package com.example.DigitalBookingBEG6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Column(name="username")
    @NotEmpty
    private String username;
    @Column(name="nombre")
    @NotEmpty
    private String usuarioNombre;
    @Column(name="apellido")
    @NotEmpty
    private String usuarioApellido;
    @Column(name="email")
    @Email
    @NotEmpty
    @Size(min = 6, max = 20)
    private String usuarioEmail;
    @Column(name="password")
    @NotEmpty
    private String usuarioPassword;
    @Column(name="ciudad")
    private String usuarioCiudad;
    @ManyToOne
    @JoinColumn(name="id_rol")
    @NotNull
    private Rol usuarioRol;

    public Usuario (Integer id){
        this.usuarioId = id;
    }

    public Usuario (String username){
        this.username = username;
    }
}
