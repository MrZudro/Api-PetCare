package edu.sena.petcare.dto.Pet;

import lombok.Data;
import java.util.List;

@Data
public class PetDetailDTO {
    // Basic Pet Info
    private Long id;
    private String name;
    private String birthdate;
    private String specieName;
    private String raceName;
    private String gender;
    private String color;
    private String weight;
    private String microchip;
    private String imageUrl;
    private String allergies;
    private int age;

    // Nested collections
    private List<ActiveConditionDTO> activeConditions;
    private List<VaccinationDTO> vaccinations;
    private List<ConsultationDTO> consultations;
    private List<PrescriptionDTO> activePrescriptions;

    @Data
    public static class ActiveConditionDTO {
        private Long id;
        private String conditionName;
        private String description;
        private String icon;
        private String startDate;
        private String endDate;
    }

    @Data
    public static class VaccinationDTO {
        private Long id;
        private String vaccineName;
        private String applicationDate;
        private String nextDueDate;
        private String lotNumber;
        private String employeeName;
        private String clinicName;
        private String observations;
        private String certificate;
    }

    @Data
    public static class ConsultationDTO {
        private Long id;
        private String date;
        private String reason;
        private String symptoms;
        private String diagnosis;
        private String treatment;
        private String weight;
        private String temperature;
        private String physicalExamination;
        private String notes;
        private String employeeName;
        private String clinicName;
    }

    @Data
    public static class PrescriptionDTO {
        private Long id;
        private String medicationName;
        private String prescribedDate;
        private String validUntil;
        private String dosage;
        private String frequency;
        private String notes;
        private String employeeName;
    }
}
