package edu.sena.petcare.repositories;


import edu.sena.petcare.models.Species;
import edu.sena.petcare.models.enums.SpeciesStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    // Buscar todas las especies que estén ACTIVAS
    List<Species> findAllByStatus(SpeciesStatus status);

    // Buscar una especie por ID y que esté ACTIVA
    Optional<Species> findByIdAndStatus(Long id, SpeciesStatus status);

    // Para validar que el nombre no se repita
    Optional<Species> findByNameIgnoreCase(String name);
}