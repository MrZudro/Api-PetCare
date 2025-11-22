package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.documenttype.DocumentTypeNewUpdateDTO;
import edu.sena.petcare.dto.documenttype.DocumentTypeReadDTO;
import edu.sena.petcare.models.DocumentType;

@Component
public class DocumentTypeMapper {

    public DocumentTypeReadDTO toDto(DocumentType documentType) {
        if (documentType == null) {
            return null;
        }
        DocumentTypeReadDTO dto = new DocumentTypeReadDTO();
        dto.setId(documentType.getId());
        dto.setName(documentType.getName());
        dto.setAbreviation(documentType.getAbreviation());
        return dto;
    }

    public DocumentType toEntity(DocumentTypeNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        DocumentType documentType = new DocumentType();
        documentType.setName(dto.getName());
        documentType.setAbreviation(dto.getAbreviation());
        return documentType;
    }

    public void updateEntity(DocumentTypeNewUpdateDTO dto, DocumentType documentType) {
        if (dto == null || documentType == null) {
            return;
        }
        documentType.setName(dto.getName());
        documentType.setAbreviation(dto.getAbreviation());
    }
}
