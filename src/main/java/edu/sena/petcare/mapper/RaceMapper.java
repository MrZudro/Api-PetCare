package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.race.RaceNewUpdateDTO;
import edu.sena.petcare.dto.race.RaceReadDTO;
import edu.sena.petcare.models.Race;

@Component
public class RaceMapper {

    public RaceReadDTO toDto(Race race) {
        if (race == null) {
            return null;
        }
        RaceReadDTO dto = new RaceReadDTO();
        dto.setId(race.getId());
        dto.setName(race.getName());

        if (race.getEspecie() != null) {
            dto.setSpecieId(race.getEspecie().getId());
            dto.setSpecieName(race.getEspecie().getName());
        }

        return dto;
    }

    public Race toEntity(RaceNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Race race = new Race();
        race.setName(dto.getName());
        // Note: Specie relationship should be set in service layer
        return race;
    }

    public void updateEntity(RaceNewUpdateDTO dto, Race race) {
        if (dto == null || race == null) {
            return;
        }
        race.setName(dto.getName());
        // Note: Specie relationship should be updated in service layer
    }
}
