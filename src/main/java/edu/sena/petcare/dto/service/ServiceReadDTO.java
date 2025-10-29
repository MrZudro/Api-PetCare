package edu.sena.petcare.dto.service;

import edu.sena.petcare.models.enums.StatusService;
import lombok.Data;

@Data
public class ServiceReadDTO {
    private Long id;
    private StatusService status;
    private String name;
    private String description;
    private String picture;
}
