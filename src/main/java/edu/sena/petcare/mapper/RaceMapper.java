package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.race.RaceNewUpdateDTO;
import edu.sena.petcare.dto.race.RaceReadDTO;
import edu.sena.petcare.models.Race;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RaceMapper {

    //1.Entidad -> ReadDTO
    @Mapping(source = "species.id", target = "speciesId")
    @Mapping(source = "status", target = "status")
    RaceReadDTO toReadDTO(Race race);

    //2. NewUpdateDTO ->Entidad (para crear)
    Race toEntity(RaceNewUpdateDTO dto);

    //3. NewUdateDTO ->Entidad (para actualizar)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "species", ignore = true)
    void updateEntityFromDto(RaceNewUpdateDTO dto, @MappingTarget Race race);

}
