package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.Pet.PetDetailDTO;
import edu.sena.petcare.models.Pet;
import edu.sena.petcare.models.PetConditions;
import edu.sena.petcare.models.VaccinationHistory;
import edu.sena.petcare.models.Consultation;
import edu.sena.petcare.models.HistoryRecipes;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PetDetailMapper {

    public PetDetailDTO toPetDetailDTO(Pet pet,
            List<PetConditions> activeConditions,
            List<VaccinationHistory> vaccinations,
            List<Consultation> consultations,
            List<HistoryRecipes> activePrescriptions) {
        if (pet == null) {
            return null;
        }

        PetDetailDTO dto = new PetDetailDTO();

        // Basic Info
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setBirthdate(pet.getBirthdate() != null ? pet.getBirthdate().toString() : null);
        dto.setGender(pet.getGender());
        dto.setColor(pet.getColor());
        dto.setWeight(pet.getWeight() != null ? pet.getWeight().toString() : null);
        dto.setMicrochip(pet.getMicrochip());
        dto.setImageUrl(pet.getImageUrl());
        dto.setAllergies(pet.getAllergies());

        if (pet.getRaza().getEspecie() != null) {
            dto.setSpecieName(pet.getRaza().getEspecie().getName());
        }
        if (pet.getRaza() != null) {
            dto.setRaceName(pet.getRaza().getName());
        }

        // Calculate Age (simplified)
        if (pet.getBirthdate() != null) {
            dto.setAge(java.time.Period.between(pet.getBirthdate(), java.time.LocalDate.now()).getYears());
        }

        // Map Collections
        dto.setActiveConditions(activeConditions.stream().map(this::toConditionDTO).collect(Collectors.toList()));
        dto.setVaccinations(vaccinations.stream().map(this::toVaccinationDTO).collect(Collectors.toList()));
        dto.setConsultations(consultations.stream().map(this::toConsultationDTO).collect(Collectors.toList()));
        dto.setActivePrescriptions(
                activePrescriptions.stream().map(this::toPrescriptionDTO).collect(Collectors.toList()));

        return dto;
    }

    private PetDetailDTO.ActiveConditionDTO toConditionDTO(PetConditions entity) {
        PetDetailDTO.ActiveConditionDTO dto = new PetDetailDTO.ActiveConditionDTO();
        dto.setId(entity.getId());
        if (entity.getCondition() != null) {
            dto.setConditionName(entity.getCondition().getName());
            dto.setDescription(entity.getCondition().getDescription());
            dto.setIcon(entity.getCondition().getIcon());
        }
        dto.setStartDate(entity.getFechaInicio().toString());
        dto.setEndDate(entity.getFechaFin().toString());
        return dto;
    }

    private PetDetailDTO.VaccinationDTO toVaccinationDTO(VaccinationHistory entity) {
        PetDetailDTO.VaccinationDTO dto = new PetDetailDTO.VaccinationDTO();
        dto.setId(entity.getId());
        if (entity.getVaccine() != null) {
            dto.setVaccineName(entity.getVaccine().getName());
        }
        dto.setApplicationDate(entity.getApplicationDate().toString());
        dto.setNextDueDate(entity.getNextDueDate() != null ? entity.getNextDueDate().toString() : null);
        dto.setLotNumber(entity.getLotNumber());
        dto.setObservations(entity.getObservations());
        dto.setCertificate(entity.getCertificate());

        if (entity.getEmployee() != null) {
            dto.setEmployeeName(entity.getEmployee().getNames());
        }
        if (entity.getVeterinaryClinic() != null) {
            dto.setClinicName(entity.getVeterinaryClinic().getName());
        }
        return dto;
    }

    private PetDetailDTO.ConsultationDTO toConsultationDTO(Consultation entity) {
        PetDetailDTO.ConsultationDTO dto = new PetDetailDTO.ConsultationDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getConsultationDateTime().toString());
        dto.setReason(entity.getReason());
        dto.setSymptoms(entity.getSymptoms());
        dto.setDiagnosis(entity.getDiagnosis());
        dto.setTreatment(entity.getTreatment());
        dto.setWeight(entity.getWeight() != null ? entity.getWeight().toString() : null);
        dto.setTemperature(entity.getTemperature() != null ? entity.getTemperature().toString() : null);
        dto.setPhysicalExamination(entity.getPhysicalExamination());
        dto.setNotes(entity.getNotes());

        if (entity.getEmployee() != null) {
            dto.setEmployeeName(entity.getEmployee().getNames());
        }
        if (entity.getVeterinaryClinic() != null) {
            dto.setClinicName(entity.getVeterinaryClinic().getName());
        }
        return dto;
    }

    private PetDetailDTO.PrescriptionDTO toPrescriptionDTO(HistoryRecipes entity) {
        PetDetailDTO.PrescriptionDTO dto = new PetDetailDTO.PrescriptionDTO();
        dto.setId(entity.getId());
        if (entity.getRecipe() != null) {
            dto.setMedicationName(entity.getRecipe().getMedicationName());
        }
        dto.setPrescribedDate(entity.getPrescribedDate().toString());
        dto.setValidUntil(entity.getValidUntil().toString());
        dto.setDosage(entity.getDosage());
        dto.setFrequency(entity.getFrequency());
        dto.setNotes(entity.getNotes());

        if (entity.getEmployee() != null) {
            dto.setEmployeeName(entity.getEmployee().getNames());
        }
        return dto;
    }
}
