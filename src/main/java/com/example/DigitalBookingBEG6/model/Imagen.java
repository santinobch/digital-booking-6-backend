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
    private Integer imagenId;
    @Column(name="titulo")
    private String imagenTitulo;
    @Column(name="url")
    @NotEmpty
    private String imagenUrl;
    @JsonBackReference
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="id_producto")
    private Producto producto;

    public Imagen(String titulo, String url){
        this.imagenTitulo = titulo;
        this.imagenUrl = url;
    }

    public Imagen(String imagenUrl){
        this.imagenUrl = imagenUrl;
    }
}
