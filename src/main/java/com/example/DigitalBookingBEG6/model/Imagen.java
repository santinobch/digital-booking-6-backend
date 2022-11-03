package com.example.DigitalBookingBEG6.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_imagen")
    private Integer id;
    @Column
    private String titulo;
    @Column
    private String url;

    public Imagen(String titulo, String url){
        this.titulo = titulo;
        this.url = url;
    }
}
