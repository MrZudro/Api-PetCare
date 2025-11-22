package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.specie.SpecieNewUpdateDTO;
import edu.sena.petcare.dto.specie.SpecieReadDTO;
import edu.sena.petcare.models.Specie;

@Component
public class SpecieMapper {

    public SpecieReadDTO toReadDTO(Specie specie) {
        if (specie == null) {
            return null;
        }
        SpecieReadDTO dto = new SpecieReadDTO();
        dto.setId(specie.getId());
        dto.setName(specie.getName());
        return dto;
    }

    public Specie toEntity(SpecieNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Specie specie = new Specie();
        specie.setName(dto.getName());
        return specie;
    }

    public void updateEntity(SpecieNewUpdateDTO dto, Specie specie) {
        if (dto == null || specie == null) {
            return;
        }
        specie.setName(dto.getName());
    }
}
