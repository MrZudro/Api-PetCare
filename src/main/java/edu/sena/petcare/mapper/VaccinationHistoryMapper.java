package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryCreateDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryUpdateDTO;
import edu.sena.petcare.models.VaccinationHistory;
import edu.sena.petcare.utility.ListaMappeo;
import java.time.LocalDate;
import java.util.List;

@Component
public class VaccinationHistoryMapper {

    public VaccinationHistoryReadDTO toDto(VaccinationHistory entity) {
        if (entity == null) {
            return null;
        }
        return new VaccinationHistoryReadDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getFechaAplicacion() != null ? entity.getFechaAplicacion().toString() : null,
                entity.isActiva());
    }

    public VaccinationHistory toEntity(VaccinationHistoryCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        VaccinationHistory v = new VaccinationHistory();
        v.setNombre(dto.getNombre());
        v.setDescripcion(dto.getDescripcion());
        if (dto.getFechaAplicacion() != null && !dto.getFechaAplicacion().isEmpty()) {
            v.setFechaAplicacion(LocalDate.parse(dto.getFechaAplicacion()));
        }
        v.setActiva(true);
        return v;
    }

    public void updateEntity(VaccinationHistoryUpdateDTO dto, VaccinationHistory entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getNombre() != null)
            entity.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null)
            entity.setDescripcion(dto.getDescripcion());
        if (dto.getFechaAplicacion() != null && !dto.getFechaAplicacion().isEmpty()) {
            entity.setFechaAplicacion(LocalDate.parse(dto.getFechaAplicacion()));
        }
        entity.setActiva(dto.isActiva());
    }

    public List<VaccinationHistoryReadDTO> toDtoList(List<VaccinationHistory> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}