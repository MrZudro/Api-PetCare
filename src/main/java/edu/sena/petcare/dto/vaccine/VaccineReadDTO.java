package edu.sena.petcare.dto.vaccine;

import java.time.LocalDate;


public class VaccineReadDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDate applicationDate;
    private boolean active;

    public VaccineReadDTO() {}

    public VaccineReadDTO(Long id, String name, String description, LocalDate applicationDate, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.applicationDate = applicationDate;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
