package edu.sena.petcare.mapper;


import edu.sena.petcare.dto.species.SpeciesNewUpdateDTO;
import edu.sena.petcare.dto.species.SpeciesReadDTO;
import edu.sena.petcare.models.Species;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {

    // 1. Entidad -> ReadDTO
    @Mapping(source = "status", target = "status")
    SpeciesReadDTO toReadDTO(Species species);

    // 2. NewUpdateDTO -> Entidad (para crear)
    Species toEntity(SpeciesNewUpdateDTO dto);

    // 3. NewUpdateDTO -> Entidad (para actualizar)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true) // El estado no se actualiza desde el DTO
    void updateEntityFromDto(SpeciesNewUpdateDTO dto, @MappingTarget Species species);
}