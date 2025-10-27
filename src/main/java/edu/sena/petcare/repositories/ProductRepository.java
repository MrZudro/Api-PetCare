package edu.sena.petcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sena.petcare.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Consulta los productos que tienen al menos una subcategoría con el ID dado.
    List<Product> findBySubcategorias_Id(Long subcategoriaId);

    //Consulta cruzada para buscar producto por categoria
    @Query("SELECT p FROM Producto p JOIN p.subcategorias s WHERE s.categoria.id = :categoriaId")
    List<Product> findByCategoriaId(@Param("categoriaId") Long categoriaId);
}
