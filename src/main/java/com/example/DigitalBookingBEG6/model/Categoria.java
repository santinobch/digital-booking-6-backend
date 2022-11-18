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
    private Integer id;
    @Column
    @NotEmpty
    private String titulo;
    @Column
    @NotNull
    private String descripcion;
    @Column
    private String url;

    public Categoria(String titulo, String descripcion, String url) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
    }
}
