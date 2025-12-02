package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.sena.petcare.models.Consultation;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    /**
     * @Documented Busca el historial de consultas por una mascota específica.
     * @param petId ID de la mascota.
     * @return Lista de consultas de la mascota.
     */
    List<Consultation> findByPetIdOrderByConsultationDateTimeDesc(Long petId);

    /**
     * @Documented Busca el historial de consultas realizadas por un empleado.
     * @param employeeId ID del empleado (Veterinario).
     * @return Lista de consultas realizadas.
     */
    List<Consultation> findByEmployeeId(Long employeeId);
}