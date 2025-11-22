package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.Pet.PetCreateDTO;
import edu.sena.petcare.dto.Pet.PetReadDTO;
import edu.sena.petcare.dto.Pet.PetUpdateDTO;
import edu.sena.petcare.models.Pet;
import edu.sena.petcare.utility.ListaMappeo;
import java.time.LocalDate;
import java.util.List;

@Component
public class PetMapper {

    public PetReadDTO toDto(Pet entity) {
        if (entity == null) {
            return null;
        }
        String raceName = entity.getRaza() != null ? entity.getRaza().getName() : null;
        String userNames = entity.getUser() != null ? entity.getUser().getNames() : null;

        return new PetReadDTO(
                entity.getId(),
                entity.getImageUrl(),
                entity.getName(),
                entity.getBirthdate() != null ? entity.getBirthdate().toString() : null,
                entity.getMicrochip(),
                entity.getColor(),
                entity.getWeight(),
                entity.getGender(),
                raceName,
                userNames);
    }

    public Pet toEntity(PetCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Pet pet = new Pet();
        pet.setImageUrl(dto.getImageUrl());
        pet.setName(dto.getName());
        if (dto.getBirthdate() != null && !dto.getBirthdate().isEmpty()) {
            pet.setBirthdate(LocalDate.parse(dto.getBirthdate()));
        }
        pet.setMicrochip(dto.getMicrochip());
        pet.setColor(dto.getColor());
        pet.setWeight(dto.getWeight());
        pet.setGender(dto.getGender());
        // Relationships (Race, User) handled in Service
        return pet;
    }

    public void updateEntity(PetUpdateDTO dto, Pet entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getImageUrl() != null)
            entity.setImageUrl(dto.getImageUrl());
        if (dto.getColor() != null)
            entity.setColor(dto.getColor());
        if (dto.getWeight() != null)
            entity.setWeight(dto.getWeight());
    }

    public List<PetReadDTO> toDtoList(List<Pet> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}
