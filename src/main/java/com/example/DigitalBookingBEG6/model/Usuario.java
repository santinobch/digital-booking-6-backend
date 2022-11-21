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
    private Integer id;
    @Column
    @NotEmpty
    private String username;
    @Column
    @NotEmpty
    private String nombre;
    @Column
    @NotEmpty
    private String apellido;
    @Column
    @Email
    @NotEmpty
    private String email;
    @Column
    @NotEmpty
    private String password;
    @Column
    private String ciudad;
    @ManyToOne
    @JoinColumn(name="id_rol")
    @NotNull
    private Rol rol;

    public Usuario (Integer id){
        this.id = id;
    }
}
