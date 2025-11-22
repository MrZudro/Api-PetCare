package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.subcategory.SubcategoryNewUpdateDTO;
import edu.sena.petcare.dto.subcategory.SubcategoryReadDTO;
import edu.sena.petcare.models.Subcategory;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.List;

@Component
public class SubcategoryMapper {

    public SubcategoryReadDTO toDto(Subcategory entity) {
        if (entity == null) {
            return null;
        }
        SubcategoryReadDTO dto = new SubcategoryReadDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategoryId(entity.getCategoria() != null ? entity.getCategoria().getId() : null);
        dto.setCategoryName(entity.getCategoria() != null ? entity.getCategoria().getName() : null);
        return dto;
    }

    public Subcategory toEntity(SubcategoryNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Subcategory entity = new Subcategory();
        entity.setName(dto.getName());
        // Category relationship handled in Service
        return entity;
    }

    public void updateEntity(SubcategoryNewUpdateDTO dto, Subcategory entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getName() != null)
            entity.setName(dto.getName());
        // Category relationship handled in Service
    }

    public List<SubcategoryReadDTO> toDtoList(List<Subcategory> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}
