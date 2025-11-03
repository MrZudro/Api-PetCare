package edu.sena.petcare.dto.vaccine;

import java.time.LocalDate;

//crear nueva vacuna
public class VaccineCreateDTO {

    private String name;
    private String description;
    private LocalDate applicationDate;

    public VaccineCreateDTO() {}

    public VaccineCreateDTO(String name, String description, LocalDate applicationDate) {
        this.name = name;
        this.description = description;
        this.applicationDate = applicationDate;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
}
