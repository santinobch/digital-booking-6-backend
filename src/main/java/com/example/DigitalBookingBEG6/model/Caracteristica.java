package com.example.DigitalBookingBEG6.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="caracteristicas")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_caracteristica")
    private Integer id;
    @Column(name="nombre")
    @NotEmpty
    private String caracteristicaNombre;
    @Column(name="icono")
    private String caracteristicaIcono;

    public Caracteristica(String nombre, String icono){
        this.caracteristicaNombre = nombre;
        this.caracteristicaIcono = icono;
    }

    public Caracteristica (Integer id){
        this.id = id;
    }
}
