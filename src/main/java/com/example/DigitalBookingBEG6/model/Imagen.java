package com.example.DigitalBookingBEG6.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String titulo;
    @Column
    @NotEmpty
    private String url;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_producto")
    @NotEmpty
    private Producto producto;

    public Imagen(String titulo, String url){
        this.titulo = titulo;
        this.url = url;
    }
}
