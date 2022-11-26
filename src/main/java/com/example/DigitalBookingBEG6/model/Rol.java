package com.example.DigitalBookingBEG6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    private Integer rolId;
    @Column(name="nombre")
    @NotEmpty
    private String rolNombre;

    public Rol (String rolNombre){
        this.rolNombre = rolNombre;
    }
}
