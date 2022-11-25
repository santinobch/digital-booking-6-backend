package com.example.DigitalBookingBEG6.repository;

import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * from productos ORDER BY RAND() LIMIT 4", nativeQuery = true)
    List<Producto> randomProducts();

    @Query("SELECT p FROM Producto p WHERE p.ciudad.id = ?1")
    List<Producto> getProductosByCiudad(Integer id);

    @Query("SELECT p FROM Producto p WHERE p.categoria.id = ?1")
    List<Producto> getProductosByCategoria(Integer id);

    @Query(value = "SELECT * from productos p " +
            "WHERE p.id_producto NOT IN ( " +
            "    SELECT DISTINCT r.id_producto FROM reservas r " +
            "    WHERE (r.fecha_hasta > ?1 and r.fecha_desde < ?2) " +
            ")" +
            " group by p.id_producto; ", nativeQuery = true)
    List<Producto> getProductosBetweenDates(LocalDate fechaInicial, LocalDate fechaFinal);

    @Query(value = "SELECT * from productos p " +
                    "WHERE p.id_ciudad = ?1 " +
                    "AND p.id_producto NOT IN ( " +
                    "    SELECT DISTINCT r.id_producto FROM reservas r " +
                    "    WHERE (r.fecha_hasta > ?2 and r.fecha_desde < ?3) " +
                    ")" +
                    " group by p.id_producto; ", nativeQuery = true)
    List<Producto> getProductosByCityAndBetweenDates(Integer ciudades_id, LocalDate fechaInicial, LocalDate fechaFinal);

    Producto findByProductoTitulo(String titulo);
}
