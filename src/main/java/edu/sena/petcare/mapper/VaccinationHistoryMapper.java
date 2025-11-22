package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryCreateDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryUpdateDTO;
import edu.sena.petcare.models.VaccinationHistory;
import java.time.LocalDate;

public class VaccinationHistoryMapper {

    public static VaccinationHistory toEntity(VaccinationHistoryCreateDTO dto) {
        VaccinationHistory v = new VaccinationHistory();
        v.setNombre(dto.getNombre());
        v.setDescripcion(dto.getDescripcion());
        v.setFechaAplicacion(LocalDate.parse(dto.getFechaAplicacion()));
        v.setActiva(true);
        return v;
    }

    public static VaccinationHistoryReadDTO toReadDTO(VaccinationHistory entity) {
        return new VaccinationHistoryReadDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getFechaAplicacion().toString(),
                entity.isActiva()
        );
    }

    public static void updateEntity(VaccinationHistory entity, VaccinationHistoryUpdateDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaAplicacion(LocalDate.parse(dto.getFechaAplicacion()));
        entity.setActiva(dto.isActiva());
    }
}