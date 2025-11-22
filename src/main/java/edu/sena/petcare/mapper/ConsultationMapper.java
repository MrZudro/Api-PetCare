package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.consultation.ConsultationNewDTO;
import edu.sena.petcare.dto.consultation.ConsultationReadDTO;
import edu.sena.petcare.models.Consultation;
import edu.sena.petcare.utility.ListaMappeo;

import java.util.List;

@Component
public class ConsultationMapper {

    // Mapeo de Entity a DTO
    public ConsultationReadDTO toDto(Consultation entity) {
        if (entity == null) {
            return null;
        }

        ConsultationReadDTO dto = new ConsultationReadDTO();

        dto.setId(entity.getId());
        dto.setEmployeeName(entity.getEmployee() != null ? entity.getEmployee().getNames() : null);
        dto.setVeterinaryClinicName(
                entity.getVeterinaryClinic() != null ? entity.getVeterinaryClinic().getName() : null);
        dto.setPetId(entity.getPet() != null ? entity.getPet().getId() : null);
        dto.setPetName(entity.getPet() != null ? entity.getPet().getName() : null);
        dto.setConsultationDateTime(entity.getConsultationDateTime());
        dto.setSymptoms(entity.getSymptoms());
        dto.setDiagnosis(entity.getDiagnosis());
        dto.setTreatment(entity.getTreatment());
        dto.setNotes(entity.getNotes());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    // Mapeo de DTO a Entity
    public Consultation toEntity(ConsultationNewDTO dto) {
        if (dto == null) {
            return null;
        }

        Consultation entity = new Consultation();

        entity.setConsultationDateTime(dto.getConsultationDateTime());
        entity.setSymptoms(dto.getSymptoms());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setTreatment(dto.getTreatment());
        entity.setNotes(dto.getNotes());

        // Las relaciones (Employee, VeterinaryClinic, Pet) deben ser manejadas en la
        // capa de servicio

        return entity;
    }

    // Actualización de Entity desde DTO
    public void updateEntity(ConsultationNewDTO dto, Consultation entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getConsultationDateTime() != null)
            entity.setConsultationDateTime(dto.getConsultationDateTime());
        if (dto.getSymptoms() != null)
            entity.setSymptoms(dto.getSymptoms());
        if (dto.getDiagnosis() != null)
            entity.setDiagnosis(dto.getDiagnosis());
        if (dto.getTreatment() != null)
            entity.setTreatment(dto.getTreatment());
        if (dto.getNotes() != null)
            entity.setNotes(dto.getNotes());

        // Las relaciones deben actualizarse en la capa de servicio
    }

    // Mapeo de lista de entidades
    public List<ConsultationReadDTO> toDtoList(List<Consultation> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}