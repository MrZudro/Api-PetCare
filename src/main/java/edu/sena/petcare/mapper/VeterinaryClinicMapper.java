package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.veterinaryclinic.VeterinaryClinicNewUpdateDTO;
import edu.sena.petcare.dto.veterinaryclinic.VeterinaryClinicReadDTO;
import edu.sena.petcare.models.VeterinaryClinic;

@Component
public class VeterinaryClinicMapper {

    public VeterinaryClinicReadDTO toReadDTO(VeterinaryClinic clinic) {
        if (clinic == null) {
            return null;
        }
        VeterinaryClinicReadDTO dto = new VeterinaryClinicReadDTO();
        dto.setId(clinic.getId());
        dto.setName(clinic.getName());
        dto.setAddress(clinic.getAddress());
        dto.setPhone(clinic.getPhone());
        dto.setEmail(clinic.getEmail());
        dto.setDocumentNumber(clinic.getDocumentNumber());

        if (clinic.getDocumentTypeVeterinary() != null) {
            dto.setDocumentTypeId(clinic.getDocumentTypeVeterinary().getId());
            dto.setDocumentTypeName(clinic.getDocumentTypeVeterinary().getName());
        }

        return dto;
    }

    public VeterinaryClinic toEntity(VeterinaryClinicNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        VeterinaryClinic clinic = new VeterinaryClinic();
        clinic.setName(dto.getName());
        clinic.setAddress(dto.getAddress());
        clinic.setPhone(dto.getPhone());
        clinic.setEmail(dto.getEmail());
        clinic.setDocumentNumber(dto.getDocumentNumber());
        return clinic;
    }

    public void updateEntity(VeterinaryClinicNewUpdateDTO dto, VeterinaryClinic clinic) {
        if (dto == null || clinic == null) {
            return;
        }
        clinic.setName(dto.getName());
        clinic.setAddress(dto.getAddress());
        clinic.setPhone(dto.getPhone());
        clinic.setEmail(dto.getEmail());
        clinic.setDocumentNumber(dto.getDocumentNumber());
    }
}
