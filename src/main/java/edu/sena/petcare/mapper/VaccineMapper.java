package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.vaccine.VaccineNewUpdateDTO;
import edu.sena.petcare.dto.vaccine.VaccineReadDTO;
import edu.sena.petcare.models.Vaccine;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.List;

@Component
public class VaccineMapper {

    public VaccineReadDTO toReadDTO(Vaccine vaccine) {
        if (vaccine == null) {
            return null;
        }

        VaccineReadDTO dto = new VaccineReadDTO();
        dto.setId(vaccine.getId());
        dto.setName(vaccine.getName());
        dto.setDescription(vaccine.getDescription());
        dto.setManufacturer(vaccine.getManufacturer());
        dto.setRecommendedAgeMonths(vaccine.getRecommendedAgeMonths());
        dto.setValidityMonths(vaccine.getValidityMonths());
        return dto;
    }

    public Vaccine toEntity(VaccineNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        Vaccine vaccine = new Vaccine();
        vaccine.setName(dto.getName());
        vaccine.setDescription(dto.getDescription());
        vaccine.setManufacturer(dto.getManufacturer());
        vaccine.setRecommendedAgeMonths(dto.getRecommendedAgeMonths());
        vaccine.setValidityMonths(dto.getValidityMonths());
        return vaccine;
    }

    public void updateEntity(VaccineNewUpdateDTO dto, Vaccine vaccine) {
        if (dto == null || vaccine == null) {
            return;
        }
        if (dto.getName() != null)
            vaccine.setName(dto.getName());
        if (dto.getDescription() != null)
            vaccine.setDescription(dto.getDescription());
        if (dto.getManufacturer() != null)
            vaccine.setManufacturer(dto.getManufacturer());
        if (dto.getRecommendedAgeMonths() != null)
            vaccine.setRecommendedAgeMonths(dto.getRecommendedAgeMonths());
        if (dto.getValidityMonths() != null)
            vaccine.setValidityMonths(dto.getValidityMonths());
    }

    public List<VaccineReadDTO> toReadDtoList(List<Vaccine> entities) {
        return ListaMappeo.toDtoList(entities, this::toReadDTO);
    }
}
