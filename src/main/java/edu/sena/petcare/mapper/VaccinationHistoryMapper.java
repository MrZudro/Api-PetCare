package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.vaccinationhistory.VaccinationHistoryNewUpdateDTO;
import edu.sena.petcare.dto.vaccinationhistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.models.VaccinationHistory;
import edu.sena.petcare.models.Pet;
import edu.sena.petcare.models.Vaccine;
import edu.sena.petcare.models.Employee;
import edu.sena.petcare.models.VeterinaryClinic;
import edu.sena.petcare.repositories.PetRepository;
import edu.sena.petcare.repositories.VaccineRepository;
import edu.sena.petcare.repositories.EmployeeRepository;
import edu.sena.petcare.repositories.VeterinaryClinicRepository;
import edu.sena.petcare.utility.ListaMappeo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VaccinationHistoryMapper {

    private final PetRepository petRepository;
    private final VaccineRepository vaccineRepository;
    private final EmployeeRepository employeeRepository;
    private final VeterinaryClinicRepository veterinaryClinicRepository;

    public VaccinationHistoryReadDTO toReadDTO(VaccinationHistory entity) {
        if (entity == null) {
            return null;
        }

        VaccinationHistoryReadDTO dto = new VaccinationHistoryReadDTO();
        dto.setId(entity.getId());
        dto.setApplicationDate(entity.getApplicationDate());
        dto.setNextDueDate(entity.getNextDueDate());
        dto.setLotNumber(entity.getLotNumber());
        dto.setObservations(entity.getObservations());
        dto.setCertificate(entity.getCertificate());

        if (entity.getVaccine() != null) {
            dto.setVaccineName(entity.getVaccine().getName());
        }
        if (entity.getPet() != null) {
            dto.setPetName(entity.getPet().getName());
        }
        if (entity.getEmployee() != null) {
            // Employee extends User
            dto.setEmployeeName(entity.getEmployee().getNames());
        }
        if (entity.getVeterinaryClinic() != null) {
            dto.setVeterinaryClinicName(entity.getVeterinaryClinic().getName());
        }

        return dto;
    }

    public VaccinationHistory toEntity(VaccinationHistoryNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        VaccinationHistory entity = new VaccinationHistory();
        entity.setApplicationDate(dto.getApplicationDate());
        entity.setNextDueDate(dto.getNextDueDate());
        entity.setLotNumber(dto.getLotNumber());
        entity.setObservations(dto.getObservations());
        entity.setCertificate(dto.getCertificate());

        // Map relationships
        if (dto.getIdPet() != null) {
            Pet pet = petRepository.findById(dto.getIdPet())
                    .orElseThrow(() -> new RuntimeException("Pet not found with id: " + dto.getIdPet()));
            entity.setPet(pet);
        }

        if (dto.getIdVaccine() != null) {
            Vaccine vaccine = vaccineRepository.findById(dto.getIdVaccine())
                    .orElseThrow(() -> new RuntimeException("Vaccine not found with id: " + dto.getIdVaccine()));
            entity.setVaccine(vaccine);
        }

        if (dto.getIdEmployee() != null) {
            Employee employee = employeeRepository.findById(dto.getIdEmployee())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getIdEmployee()));
            entity.setEmployee(employee);
        }

        if (dto.getIdVeterinaryClinic() != null) {
            VeterinaryClinic clinic = veterinaryClinicRepository.findById(dto.getIdVeterinaryClinic())
                    .orElseThrow(() -> new RuntimeException(
                            "Veterinary Clinic not found with id: " + dto.getIdVeterinaryClinic()));
            entity.setVeterinaryClinic(clinic);
        }

        return entity;
    }

    public void updateEntity(VaccinationHistoryNewUpdateDTO dto, VaccinationHistory entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getApplicationDate() != null)
            entity.setApplicationDate(dto.getApplicationDate());
        if (dto.getNextDueDate() != null)
            entity.setNextDueDate(dto.getNextDueDate());
        if (dto.getLotNumber() != null)
            entity.setLotNumber(dto.getLotNumber());
        if (dto.getObservations() != null)
            entity.setObservations(dto.getObservations());
        if (dto.getCertificate() != null)
            entity.setCertificate(dto.getCertificate());

        // Update relationships
        if (dto.getIdPet() != null) {
            Pet pet = petRepository.findById(dto.getIdPet())
                    .orElseThrow(() -> new RuntimeException("Pet not found with id: " + dto.getIdPet()));
            entity.setPet(pet);
        }

        if (dto.getIdVaccine() != null) {
            Vaccine vaccine = vaccineRepository.findById(dto.getIdVaccine())
                    .orElseThrow(() -> new RuntimeException("Vaccine not found with id: " + dto.getIdVaccine()));
            entity.setVaccine(vaccine);
        }

        if (dto.getIdEmployee() != null) {
            Employee employee = employeeRepository.findById(dto.getIdEmployee())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getIdEmployee()));
            entity.setEmployee(employee);
        }

        if (dto.getIdVeterinaryClinic() != null) {
            VeterinaryClinic clinic = veterinaryClinicRepository.findById(dto.getIdVeterinaryClinic())
                    .orElseThrow(() -> new RuntimeException(
                            "Veterinary Clinic not found with id: " + dto.getIdVeterinaryClinic()));
            entity.setVeterinaryClinic(clinic);
        }
    }

    public List<VaccinationHistoryReadDTO> toReadDtoList(List<VaccinationHistory> entities) {
        return ListaMappeo.toDtoList(entities, this::toReadDTO);
    }
}