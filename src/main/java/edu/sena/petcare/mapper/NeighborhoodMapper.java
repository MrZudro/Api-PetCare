package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.neighborhood.NeighborhoodNewUpdateDTO;
import edu.sena.petcare.dto.neighborhood.NeighborhoodReadDTO;
import edu.sena.petcare.models.Neighborhood;

@Component
public class NeighborhoodMapper {

    public NeighborhoodReadDTO toDto(Neighborhood neighborhood) {
        if (neighborhood == null) {
            return null;
        }
        NeighborhoodReadDTO dto = new NeighborhoodReadDTO();
        dto.setId(neighborhood.getId());
        dto.setName(neighborhood.getName());

        if (neighborhood.getLocality() != null) {
            dto.setLocalityId(neighborhood.getLocality().getId());
            dto.setLocalityName(neighborhood.getLocality().getName());
        }

        return dto;
    }

    public Neighborhood toEntity(NeighborhoodNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.setName(dto.getName());
        return neighborhood;
    }

    public void updateEntity(NeighborhoodNewUpdateDTO dto, Neighborhood neighborhood) {
        if (dto == null || neighborhood == null) {
            return;
        }
        neighborhood.setName(dto.getName());
    }
}
