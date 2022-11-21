package com.example.DigitalBookingBEG6.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reservas")
public class Reserva {

    //TODO: evaluar usar DTO.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reserva")
    private Integer id;
    @Column
    @JsonFormat(pattern = "hh:mm")
    @NotEmpty
    private LocalTime hora;
    @Column(name="fecha_desde")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotEmpty
    private LocalDate fechaDesde;
    @Column(name="fecha_hasta")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotEmpty
    private LocalDate fechaHasta;
    @ManyToOne
    @JoinColumn(name="id_producto")
    @NotEmpty
    private Producto producto;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    @NotEmpty
    private Usuario usuario;

}
