package com.example.DigitalBookingBEG6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Integer id;

    @Column
    private String titulo;

    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="id_ciudad")
    private Ciudad ciudad;

    @JsonManagedReference
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes;

    @ManyToMany
    @JoinTable(name = "productos_x_caracteristicas",
            joinColumns = @JoinColumn(name = "id_productos"),
            inverseJoinColumns = @JoinColumn(name="id_caracteristica"))
    private List<Caracteristica> caracteristicas;

    public Producto(String titulo, String descripcion, Categoria categoria, Ciudad ciudad, List<Imagen> imagenes, List<Caracteristica> caracteristicas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.imagenes = imagenes;
        this.caracteristicas = caracteristicas;
    }
}