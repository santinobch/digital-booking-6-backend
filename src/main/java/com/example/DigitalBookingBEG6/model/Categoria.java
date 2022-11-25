package com.example.DigitalBookingBEG6.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Integer categoriaId;
    @Column
    @NotEmpty
    private String categoriaTitulo;
    @Column
    @NotNull
    private String categoriaDescripcion;
    @Column
    private String categoriaUrl;

    public Categoria(String titulo, String descripcion, String url) {
        this.categoriaTitulo = titulo;
        this.categoriaDescripcion = descripcion;
        this.categoriaUrl = url;
    }
}
