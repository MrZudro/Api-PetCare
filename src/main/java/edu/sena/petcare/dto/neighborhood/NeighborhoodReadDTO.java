package edu.sena.petcare.dto.neighborhood;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodReadDTO {
    private Long id;
    private String name;
    private Long localityId;
    private String localityName;
}
