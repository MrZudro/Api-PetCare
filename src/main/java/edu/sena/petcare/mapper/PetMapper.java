package edu.sena.petcare.mapper;


import edu.sena.petcare.dto.pet.PetNewUpdateDTO;
import edu.sena.petcare.dto.pet.PetReadDTO;
import edu.sena.petcare.models.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PetMapper {

    // 1. Entidad -> ReadDTO
    @Mapping(source = "race.id", target = "raceId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "status", target = "status")
    PetReadDTO toReadDTO(Pet pet);

    // 2. NewUpdateDTO -> Entidad (para crear)
    Pet toEntity(PetNewUpdateDTO dto);

    // 3. NewUpdateDTO -> Entidad (para actualizar)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true) // El estado no se actualiza desde el DTO
    @Mapping(target = "race", ignore = true)   // Las relaciones no se actualizan desde el DTO
    @Mapping(target = "user", ignore = true)   // Las relaciones no se actualizan desde el DTO
    @Mapping(target = "petConditions", ignore = true) // Ignorar la colección OneToMany
    void updateEntityFromDto(PetNewUpdateDTO dto, @MappingTarget Pet pet);
}