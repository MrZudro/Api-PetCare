package edu.sena.petcare.mapper;


import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.consultation.ConsultationNewDTO;
import edu.sena.petcare.dto.consultation.ConsultationReadDTO;
import edu.sena.petcare.models.Consultation;

import java.util.List;

@Component
public class ConsultationMapper {
    //Mapeo de Dto a Entity
    public ConsultationReadDTO toDto(Consultation entity){
        //validamos primero que nuestra entidad no venga vacia para no perder el tiempo
        if(entity == null){
            return null;
        }

        //instanciamos el nuevo objeto
        ConsultationReadDTO dto = new ConsultationReadDTO();

        //Asignamos los valores..
        dto.setId(entity.getId());
        dto.setEmployeeName(entity.getEmployee().getNames());
        dto.setVeterinaryClinicName(entity.getVeterinaryClinic().getName());
        dto.setPetId(entity.getId());
        dto.setPetName(entity.getPet().getName());
        dto.setConsultationDateTime(entity.getConsultationDateTime());
        dto.setSymptoms(entity.getSymptoms());
        dto.setDiagnosis(entity.getDiagnosis());
        dto.setTreatment(entity.getTreatment());
        dto.setNotes(entity.getNotes());
        dto.setStatus(entity.getStatus());

        return dto;

    }
}