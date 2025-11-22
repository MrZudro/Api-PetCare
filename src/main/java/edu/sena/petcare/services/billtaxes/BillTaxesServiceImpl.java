package edu.sena.petcare.services.billtaxes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.billtaxes.BillTaxesReadDTO;
import edu.sena.petcare.mapper.BillTaxesMapper;
import edu.sena.petcare.repositories.BillTaxesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillTaxesServiceImpl implements BillTaxesService {

    private final BillTaxesRepository billTaxesRepository;
    private final BillTaxesMapper billTaxesMapper;

    @Override
    public List<BillTaxesReadDTO> getAll() {
        return billTaxesRepository.findAll()
                .stream()
                .map(billTaxesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillTaxesReadDTO getById(Long id) {
        return billTaxesRepository.findById(id)
                .map(billTaxesMapper::toDto)
                .orElseThrow(() -> new RuntimeException("BillTaxes not found with id: " + id));
    }
}
