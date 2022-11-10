package com.example.DigitalBookingBEG6.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
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
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_producto")
    private Producto producto;

    public Imagen(String titulo, String url){
        this.titulo = titulo;
        this.url = url;
    }
}
