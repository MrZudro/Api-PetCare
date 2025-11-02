package edu.sena.petcare.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sena.petcare.models.VeterinaryClinic;

// JpaRepository toma la Entidad (VeterinaryClinic) y el tipo de dato de su ID (Long)
public interface VeterinaryClinicRepository extends JpaRepository<VeterinaryClinic, Long> {

    // Consulta una clínica veterinaria por su número de documento.
    // Útil para verificar si un documento ya existe.
    Optional<VeterinaryClinic> findByDocumentNumber(String documentNumber);

    // Consulta una clínica veterinaria por su número y tipo de documento.
    // Esta búsqueda se alinea con la restricción de unicidad (uk_veterinary)
    // definida en la entidad VeterinaryClinic[cite: 4, 8, 9].
    Optional<VeterinaryClinic> findByDocumentNumberAndDocumentTypeVeterinary_Id(String documentNumber, Long documentTypeId);

    // Consulta una clínica veterinaria por su correo electrónico.
    Optional<VeterinaryClinic> findByEmail(String email);

    // Métodos para buscar por relaciones (aunque menos comunes para una sola entidad principal):

    // Listar clínicas por el ID de un servicio que ofrecen (asumiendo relación ManyToMany o consulta específica)
    // Se utilizaría si el servicio fuera la entidad de inicio, pero para este caso se omite la complejidad.
    // Si fuera necesario: List<VeterinaryClinic> findByVeterinaryClinicServices_Id(Long serviceId);
}