package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.petconditions.PetConditionsNewUpdateDTO;
import edu.sena.petcare.dto.petconditions.PetConditionsReadDTO;
import edu.sena.petcare.models.PetConditions;

@Component
public class PetConditionsMapper {

    public PetConditionsReadDTO toDto(PetConditions petConditions) {
        if (petConditions == null) {
            return null;
        }
        PetConditionsReadDTO dto = new PetConditionsReadDTO();
        dto.setId(petConditions.getId());
        dto.setFechaInicio(petConditions.getFechaInicio());
        dto.setFechaFin(petConditions.getFechaFin());
        return dto;
    }

    public PetConditions toEntity(PetConditionsNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        PetConditions petConditions = new PetConditions();
        petConditions.setFechaInicio(dto.getFechaInicio());
        petConditions.setFechaFin(dto.getFechaFin());
        return petConditions;
    }

    public void updateEntity(PetConditionsNewUpdateDTO dto, PetConditions petConditions) {
        if (dto == null || petConditions == null) {
            return;
        }
        petConditions.setFechaInicio(dto.getFechaInicio());
        petConditions.setFechaFin(dto.getFechaFin());
    }
}
