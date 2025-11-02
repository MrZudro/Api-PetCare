package edu.sena.petcare.services.veterinaryClinic;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.veterinaryClinic.VeterinaryClinicNewUpdateDTO;
import edu.sena.petcare.dto.veterinaryClinic.VeterinaryClinicReadDTO;
import edu.sena.petcare.exceptions.DuplicateResourceException;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.VeterinaryClinicMapper;
import edu.sena.petcare.models.DocumentType;
import edu.sena.petcare.models.VeterinaryClinic;
import edu.sena.petcare.repositories.DocumentTypeRepository;
import edu.sena.petcare.repositories.VeterinaryClinicRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinaryClinicServiceImpl implements VeterinaryClinicService {

    private final VeterinaryClinicRepository veterinaryClinicRepository;
    private final VeterinaryClinicMapper veterinaryClinicMapper;
    private final DocumentTypeRepository documentTypeRepository;

    // --- Métodos de Ayuda para Reutilización ---

    /**
     * Verifica si ya existe una clínica con el mismo número y tipo de documento.
     * Lanza una excepción si se encuentra un duplicado.
     */
    private void checkDuplicate(String documentNumber, Long documentTypeId, Long currentId) {
        Optional<VeterinaryClinic> existsVeterinaryClinic = 
            veterinaryClinicRepository.findByDocumentNumberAndDocumentTypeVeterinary_Id(documentNumber, documentTypeId);

        if (existsVeterinaryClinic.isPresent() && !existsVeterinaryClinic.get().getId().equals(currentId)) {
            throw new DuplicateResourceException(
                "La Clínica Veterinaria con documento " + documentNumber + 
                " y tipo " + documentTypeId + " ya existe."
            );
        }
    }

    /**
     * Busca la entidad DocumentType o lanza una excepción si no existe.
     */
    private DocumentType getDocumentType(Long documentTypeId) {
        return documentTypeRepository.findById(documentTypeId)
            .orElseThrow(() -> new ResourceNotFoundException("Tipo de Documento con id " + documentTypeId + " no encontrado"));
    }

    // --- Implementación de Métodos de Servicio ---

    @Override
    @SuppressWarnings("null")
    public VeterinaryClinicReadDTO newVeterinaryClinic(VeterinaryClinicNewUpdateDTO newVeterinaryClinicDTO) {
        // 1. Validar unicidad (document_number + id_document_type)
        checkDuplicate(newVeterinaryClinicDTO.getDocumentNumber(), newVeterinaryClinicDTO.getDocumentTypeId(), null);

        // 2. Mapear a entidad y buscar la dependencia (DocumentType)
        VeterinaryClinic veterinaryClinic = veterinaryClinicMapper.toEntity(newVeterinaryClinicDTO);
        
        // 3. Asignar la entidad DocumentType (relación ManyToOne)
        DocumentType documentType = getDocumentType(newVeterinaryClinicDTO.getDocumentTypeId());
        veterinaryClinic.setDocumentTypeVeterinary(documentType);

        // 4. Guardar la entidad
        VeterinaryClinic saved = veterinaryClinicRepository.save(veterinaryClinic);

        // 5. Mapear a DTO de lectura y retornar
        return veterinaryClinicMapper.toDto(saved);
    }

    @Override
    public List<VeterinaryClinicReadDTO> allVeterinaryClinics() {
        // 1. Obtener todas las entidades
        List<VeterinaryClinic> clinics = veterinaryClinicRepository.findAll();
        
        // 2. Mapear a lista de DTOs de lectura y retornar (similar a ProductServiceImpl [cite: 42])
        return clinics.stream()
            .map(veterinaryClinicMapper::toDto)
            .toList();
    }

    @Override
    public VeterinaryClinicReadDTO oneSpecificVeterinaryClinic(Long id) {
        // 1. Buscar entidad por ID
        VeterinaryClinic clinic = veterinaryClinicRepository.findById(Objects.requireNonNull(id, "El ID es obligatorio"))
            .orElseThrow(() -> new ResourceNotFoundException("Clínica Veterinaria con id " + id + " no encontrada"));
        
        // 2. Mapear a DTO de lectura y retornar (similar a ProductServiceImpl [cite: 44])
        return veterinaryClinicMapper.toDto(clinic);
    }

    @Override
    @SuppressWarnings("null")
    public VeterinaryClinicNewUpdateDTO updateVeterinaryClinic(Long id, VeterinaryClinicNewUpdateDTO updatedVeterinaryClinic) {
        Objects.requireNonNull(id, "El ID es obligatorio");
        Objects.requireNonNull(updatedVeterinaryClinic, "El DTO de actualización es obligatorio"); // similar a ProductServiceImpl [cite: 45]

        // 1. Buscar la entidad a actualizar
        VeterinaryClinic clinicToUpdate = veterinaryClinicRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Clínica Veterinaria con id " + id + " no encontrada"));

        // 2. Validar unicidad si los campos clave cambian (document_number o document_type)
        if (!updatedVeterinaryClinic.getDocumentNumber().equals(clinicToUpdate.getDocumentNumber()) ||
            !updatedVeterinaryClinic.getDocumentTypeId().equals(clinicToUpdate.getDocumentTypeVeterinary().getId())) {
            
            checkDuplicate(updatedVeterinaryClinic.getDocumentNumber(), updatedVeterinaryClinic.getDocumentTypeId(), id);
        }

        // 3. Mapear los campos del DTO a la entidad existente (usando MapStruct @MappingTarget)
        veterinaryClinicMapper.updateEntity(updatedVeterinaryClinic, clinicToUpdate);

        // 4. Actualizar la relación ManyToOne si el ID de tipo de documento cambió
        if (!updatedVeterinaryClinic.getDocumentTypeId().equals(clinicToUpdate.getDocumentTypeVeterinary().getId())) {
            DocumentType documentType = getDocumentType(updatedVeterinaryClinic.getDocumentTypeId());
            clinicToUpdate.setDocumentTypeVeterinary(documentType);
        }
        
        // 5. Guardar la entidad actualizada
        VeterinaryClinic updatedClinic = veterinaryClinicRepository.save(clinicToUpdate);

        // 6. Usamos el nuevo método toUpdateDto para retornar la entidad persistida.
        return veterinaryClinicMapper.toUpdateDto(updatedClinic);
    }

    @Override
    public void deleteVeterinaryClinic(Long id) {
        // 1. Verificar existencia y eliminar
        if (!veterinaryClinicRepository.existsById(Objects.requireNonNull(id, "El ID es obligatorio"))) { // similar a ProductServiceImpl [cite: 50]
            throw new ResourceNotFoundException("Clínica Veterinaria con id " + id + " no encontrada");
        }
        // Asumiendo que 'borrar' significa eliminar de la base de datos (hard delete)
        veterinaryClinicRepository.deleteById(id);
        // Si 'borrar' significara 'desactivar' (soft delete), se cambiaría el estado aquí
    }
}