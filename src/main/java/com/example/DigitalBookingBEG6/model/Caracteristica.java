package com.example.DigitalBookingBEG6.model;

import lombok.*;
import javax.persistence.*;

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
    @Column
    private String nombre;
    @Column
    private String icono;

    public Caracteristica(String nombre, String icono){
        this.nombre = nombre;
        this.icono = icono;
    }

    public Caracteristica (Integer id){
        this.id = id;
    }
}
