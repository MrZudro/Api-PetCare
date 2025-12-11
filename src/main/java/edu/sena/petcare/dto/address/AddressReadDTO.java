package edu.sena.petcare.dto.address;

import edu.sena.petcare.models.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressReadDTO {

    private Long id;
    private String addressLine;
    private String additionalInfo;
    private String deliveryNotes;
    private AddressType addressType;
    private Boolean isDefault;
    private Long neighborhoodId;
    private String neighborhoodName;
    private Long localityId;
    private String localityName;
    private Instant createdDate;
    private Instant updatedDate;
}
