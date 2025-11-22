package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.locality.LocalityNewUpdateDTO;
import edu.sena.petcare.dto.locality.LocalityReadDTO;
import edu.sena.petcare.models.Locality;

@Component
public class LocalityMapper {

    public LocalityReadDTO toReadDTO(Locality locality) {
        if (locality == null) {
            return null;
        }
        LocalityReadDTO dto = new LocalityReadDTO();
        dto.setId(locality.getId());
        dto.setName(locality.getName());
        return dto;
    }

    public Locality toEntity(LocalityNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Locality locality = new Locality();
        locality.setName(dto.getName());
        return locality;
    }

    public void updateEntity(LocalityNewUpdateDTO dto, Locality locality) {
        if (dto == null || locality == null) {
            return;
        }
        locality.setName(dto.getName());
    }
}
