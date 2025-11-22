package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.condition.ConditionsDTO;
import edu.sena.petcare.models.Conditions;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.List;

@Component
public class ConditionsMapper {

    public ConditionsDTO toDTO(Conditions conditions) {
        if (conditions == null) {
            return null;
        }

        ConditionsDTO dto = new ConditionsDTO();
        dto.setName(conditions.getName());
        dto.setDescription(conditions.getDescription());
        dto.setIcon(conditions.getIcon());
        return dto;
    }

    public Conditions toEntity(ConditionsDTO dto) {
        if (dto == null) {
            return null;
        }

        Conditions nueva = new Conditions();
        nueva.setName(dto.getName());
        nueva.setDescription(dto.getDescription());
        nueva.setIcon(dto.getIcon());
        return nueva;
    }

    public void updateEntity(ConditionsDTO dto, Conditions conditions) {
        if (dto == null || conditions == null) {
            return;
        }
        if (dto.getName() != null)
            conditions.setName(dto.getName());
        if (dto.getDescription() != null)
            conditions.setDescription(dto.getDescription());
        if (dto.getIcon() != null)
            conditions.setIcon(dto.getIcon());
    }

    public List<ConditionsDTO> toDtoList(List<Conditions> entities) {
        return ListaMappeo.toDtoList(entities, this::toDTO);
    }
}
