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
    private Integer reservaId;
    @Column(name="hora")
    @NotNull
    private LocalTime reservaHora;
    @Column(name="fecha_desde")
    @NotNull
    private LocalDate reservaFechaDesde;
    @Column(name="fecha_hasta")
    @NotNull
    private LocalDate reservaFechaHasta;
    @ManyToOne
    @JoinColumn(name="id_producto")
    @NotNull
    private Producto producto;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    @NotNull
    private Usuario usuario;

}
