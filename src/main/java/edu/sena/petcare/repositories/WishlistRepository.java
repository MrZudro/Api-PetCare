package edu.sena.petcare.repositories;

import edu.sena.petcare.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    /*Consulta personalizada para filtrar por la fecha en que se guardó.*/
    List<Wishlist> findByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * pss si es necesario es una consulta útil para buscar la lista de un usuario
     */
    // Optional<Wishlist> findByUserId(Long userId);
}