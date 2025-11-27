package edu.sena.petcare.dto.service;

import java.util.List;

import edu.sena.petcare.models.enums.StatusService;
import lombok.Data;

@Data
public class ServiceReadDTO {
    private Long id;
    private StatusService status;
    private String name;
    private String description;
    private String picture;
    private String imageUrl; // Frontend expects this
    private List<edu.sena.petcare.dto.veterinary.VeterinaryClinicSmallDTO> clinics; // List of clinics offering this
                                                                                    // service
}
