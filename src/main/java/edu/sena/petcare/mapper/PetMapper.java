package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.Pet.PetReadDTO;
import edu.sena.petcare.dto.Pet.PetCreateDTO;
import edu.sena.petcare.dto.Pet.PetUpdateDTO;
import edu.sena.petcare.models.*;
import java.time.LocalDate;


public class PetMapper {


    public static Pet toEntity(PetCreateDTO dto, Race race, User user) {
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

        pet.setRaza(race);
        pet.setUser(user);

        return pet;
    }

    public static PetReadDTO toReadDTO(Pet entity) {
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
        userNames
    );
}



    public static void updateEntity(Pet entity, PetUpdateDTO dto) {
        if (dto.getImageUrl() != null) entity.setImageUrl(dto.getImageUrl());
        if (dto.getColor() != null) entity.setColor(dto.getColor());
        if (dto.getWeight() != null) entity.setWeight(dto.getWeight());
    }
}

