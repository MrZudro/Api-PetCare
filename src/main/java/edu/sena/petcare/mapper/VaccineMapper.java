package edu.sena.petcare.mapper;

import edu.sena.petcare.models.Vaccine;
import edu.sena.petcare.dto.vaccine.VaccineCreateDTO;
import edu.sena.petcare.dto.vaccine.VaccineReadDTO;
import edu.sena.petcare.dto.vaccine.VaccineUpdateDTO;

public class VaccineMapper {

    public static Vaccine toEntity(VaccineCreateDTO dto) {
        Vaccine vaccine = new Vaccine();
        vaccine.setName(dto.getName());
        vaccine.setDescription(dto.getDescription());
        vaccine.setApplicationDate(dto.getApplicationDate());
        vaccine.setActive(true);
        return vaccine;
    }

    public static VaccineReadDTO toReadDTO(Vaccine entity) {
        return new VaccineReadDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getApplicationDate(),
            entity.isActive()
        );
    }

    public static void updateEntity(Vaccine entity, VaccineUpdateDTO dto) {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }

        if (dto.getApplicationDate() != null) {
            entity.setApplicationDate(dto.getApplicationDate());
        }

        entity.setActive(dto.isActive());
    }
}

