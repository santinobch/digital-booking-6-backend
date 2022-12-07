package com.example.DigitalBookingBEG6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ciudad")
    private Integer ciudadId;
    @Column(name="nombre")
    @NotEmpty
    private String ciudadNombre;
    @Column(name="pais")
    @NotEmpty
    private String ciudadPais;

    public Ciudad(String nombre, String pais){
        this.ciudadNombre = nombre;
        this.ciudadPais = pais;
    }

    public Ciudad(Integer id){
        this.ciudadId = id;
    }
}
