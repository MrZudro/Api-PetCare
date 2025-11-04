package edu.sena.petcare.repositories;

import edu.sena.petcare.models.Race;
import edu.sena.petcare.models.enums.RaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    // Buscar todas las razas que estén ACTIVAS
    List<Race> findAllByStatus(RaceStatus status);

    // Buscar una raza por ID y que esté ACTIVA
    Optional<Race> findByIdAndStatus(Long id, RaceStatus status);

    // Para validar que el nombre no se repita
    Optional<Race> findByNameIgnoreCase(String name);

    // Opcional: Buscar razas por especie (útil para filtros)
    List<Race> findBySpeciesIdAndStatus(Long speciesId, RaceStatus status);
}
