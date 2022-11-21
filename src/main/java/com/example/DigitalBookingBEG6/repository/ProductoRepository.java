package com.example.DigitalBookingBEG6.repository;

import com.example.DigitalBookingBEG6.model.Ciudad;
import com.example.DigitalBookingBEG6.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * from productos ORDER BY RAND() LIMIT 4", nativeQuery = true)
    List<Producto> randomProducts();

    @Query("SELECT p FROM Producto p WHERE p.ciudad.id = ?1")
    List<Producto> getProductosByCiudad(Integer id);

    @Query("SELECT p FROM Producto p WHERE p.categoria.id = ?1")
    List<Producto> getProductosByCategoria(Integer id);

    Producto findByTitulo(String titulo);
}
