package edu.sena.petcare.dto.vaccine;

import java.time.LocalDate;

public class VaccineUpdateDTO {

    private String name;
    private String description;
    private LocalDate applicationDate;
    private boolean active;

    public VaccineUpdateDTO() {}

    public VaccineUpdateDTO(String name, String description, LocalDate applicationDate, boolean active) {
        this.name = name;
        this.description = description;
        this.applicationDate = applicationDate;
        this.active = active;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

