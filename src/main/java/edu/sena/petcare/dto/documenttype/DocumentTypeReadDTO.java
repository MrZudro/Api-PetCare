package edu.sena.petcare.dto.documenttype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeReadDTO {
    private Long id;
    private String name;
    private String abreviation;
}
