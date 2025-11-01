package edu.sena.petcare.repositories;


import edu.sena.petcare.models.Pet;
import edu.sena.petcare.models.enums.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // Buscar todas las mascotas que estén ACTIVAS
    List<Pet> findAllByStatus(PetStatus status);

    // Buscar una mascota por ID y que esté ACTIVA
    Optional<Pet> findByIdAndStatus(Long id, PetStatus status);

    // Para validar que el microchip sea único si se proporciona
    Optional<Pet> findByMicrochip(String microchip);

    // Opcional: Buscar todas las mascotas de un usuario específico
    List<Pet> findByUserIdAndStatus(Long userId, PetStatus status);
}