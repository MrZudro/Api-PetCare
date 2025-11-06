package edu.sena.petcare.services.consultation;

import edu.sena.petcare.dto.consultation.ConsultationNewDTO;
import edu.sena.petcare.dto.consultation.ConsultationReadDTO;

import java.util.List;

public interface ConsultationService {

    /**
     * @Documented Crea un nuevo registro de consulta. No se puede actualizar después de la creación.
     * @param dto DTO de la consulta.
     * @return DTO de lectura de la consulta creada.
     */
    ConsultationReadDTO createConsultation(ConsultationNewDTO dto);

    /**
     * @Documented Obtiene una consulta específica por ID.
     * @param id ID de la consulta.
     * @return DTO de lectura de la consulta.
     */
    ConsultationReadDTO getConsultationById(Long id);

    /**
     * @Documented Obtiene todas las consultas registradas.
     * @return Lista de todas las consultas.
     */
    List<ConsultationReadDTO> getAllConsultations();

    /**
     * @Documented Obtiene el historial de consultas de una mascota.
     * @param petId ID de la mascota.
     * @return Lista de consultas de la mascota.
     */
    List<ConsultationReadDTO> getConsultationHistoryByPet(Long petId);
    
    // NOTA: Se omiten métodos update y delete según las restricciones del requerimiento.
}