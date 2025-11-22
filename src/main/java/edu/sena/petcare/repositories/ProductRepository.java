package edu.sena.petcare.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sena.petcare.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Consulta los productos que tienen al menos una subcategoría con el ID dado.
    List<Product> findByProductSubcategories_Subcategory_Id(Long subcategoriaId);

    // Consulta cruzada para buscar producto por categoria
    @Query("SELECT DISTINCT p FROM Product p JOIN p.productSubcategories ps JOIN ps.subcategory s WHERE s.categoria.id = :categoriaId")
    List<Product> findByCategoriaId(@Param("categoriaId") Long categoriaId);

    // Consulta el producto por SKU para evitar que hayan dos productos iguales
    Optional<Product> findBySkuIgnoreCase(String sku);
}
