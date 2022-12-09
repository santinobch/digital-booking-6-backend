package com.example.DigitalBookingBEG6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private Integer productoId;
    @Column(name="titulo")
    @NotEmpty
    private String productoTitulo;
    @Column(name="descripcion")
    @NotEmpty
    private String productoDescripcion;
    @ManyToOne
    @NotNull
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name="id_ciudad")
    @NotNull
    private Ciudad ciudad;
    @JsonManagedReference
    @OneToMany(mappedBy = "producto", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @NotNull
    private List<Imagen> imagenes;

    @ManyToMany
    @JoinTable(name = "productos_x_caracteristicas",
            joinColumns = @JoinColumn(name = "id_productos"),
            inverseJoinColumns = @JoinColumn(name="id_caracteristica"))
    private List<Caracteristica> caracteristicas;
    @Column(name="house_rules_policy", length = 1000)
    private String houseRulesPolicy;
    @Column(name="health_security_policy", length = 1000)
    private String healthAndSecurityPolicy;
    @Column(name="cancellation_policy", length = 1000)
    private String cancellationPolicy;

    public Producto(String productoTitulo, String productoDescripcion, Categoria categoria, Ciudad ciudad, List<Imagen> imagenes, List<Caracteristica> caracteristicas, String houseRulesPolicy, String healthAndSecurityPolicy, String cancellationPolicy) {
        this.productoTitulo = productoTitulo;
        this.productoDescripcion = productoDescripcion;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.imagenes = imagenes;
        this.caracteristicas = caracteristicas;
        this.houseRulesPolicy = houseRulesPolicy;
        this.healthAndSecurityPolicy = healthAndSecurityPolicy;
        this.cancellationPolicy = cancellationPolicy;
    }

    public Producto(Integer id){
        this.productoId = id;
    }
}
