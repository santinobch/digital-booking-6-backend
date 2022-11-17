package com.example.DigitalBookingBEG6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    private Integer id;
    @Column
    @NotNull
    private String username;
    @Column
    @NotNull
    private String nombre;
    @Column
    @NotNull
    private String apellido;
    @Column
    @Email
    private String email;
    @Column
    private String password;
    @Column
    private String ciudad;
    @ManyToOne
    @JoinColumn(name="id_rol")
    private Rol rol;

    public Usuario (Integer id){
        this.id = id;
    }
}
