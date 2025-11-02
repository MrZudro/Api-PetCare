package edu.sena.petcare.services.veterinaryClinic;

import java.util.List;

import edu.sena.petcare.dto.veterinaryClinic.VeterinaryClinicNewUpdateDTO;
import edu.sena.petcare.dto.veterinaryClinic.VeterinaryClinicReadDTO;

public interface VeterinaryClinicService {

    /**
     * Crea una nueva clínica veterinaria.
     * @param newVeterinaryClinicDTO DTO de creación con los datos de la clínica.
     * @return DTO de lectura de la clínica creada.
     */
    VeterinaryClinicReadDTO newVeterinaryClinic(VeterinaryClinicNewUpdateDTO newVeterinaryClinicDTO);

    /**
     * Obtiene una lista de todas las clínicas veterinarias.
     * @return Lista de DTOs de lectura de las clínicas.
     */
    List<VeterinaryClinicReadDTO> allVeterinaryClinics();

    /**
     * Obtiene una clínica veterinaria específica por su ID.
     * @param id ID de la clínica a buscar.
     * @return DTO de lectura de la clínica encontrada.
     */
    VeterinaryClinicReadDTO oneSpecificVeterinaryClinic(Long id);

    /**
     * Actualiza una clínica veterinaria existente.
     * @param id ID de la clínica a actualizar.
     * @param updatedVeterinaryClinic DTO con los datos actualizados.
     * @return DTO de actualización/creación de la clínica actualizada.
     */
    VeterinaryClinicNewUpdateDTO updateVeterinaryClinic(Long id, VeterinaryClinicNewUpdateDTO updatedVeterinaryClinic);

    /**
     * Elimina (o desactiva, si aplica la lógica de negocio) una clínica veterinaria por su ID.
     * @param id ID de la clínica a eliminar.
     */
    void deleteVeterinaryClinic(Long id);
}