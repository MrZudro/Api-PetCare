package edu.sena.petcare.services.documenttype;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.documenttype.DocumentTypeReadDTO;
import edu.sena.petcare.mapper.DocumentTypeMapper;
import edu.sena.petcare.repositories.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Override
    public List<DocumentTypeReadDTO> getAll() {
        return documentTypeRepository.findAll()
                .stream()
                .map(documentTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentTypeReadDTO getById(Long id) {
        return documentTypeRepository.findById(id)
                .map(documentTypeMapper::toDto)
                .orElseThrow(() -> new RuntimeException("DocumentType not found with id: " + id));
    }
}
