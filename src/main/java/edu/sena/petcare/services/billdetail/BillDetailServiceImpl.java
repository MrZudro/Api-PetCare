package edu.sena.petcare.services.billdetail;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.billdetail.BillDetailReadDTO;
import edu.sena.petcare.mapper.BillDetailMapper;
import edu.sena.petcare.repositories.BillDetailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository billDetailRepository;
    private final BillDetailMapper billDetailMapper;

    @Override
    public List<BillDetailReadDTO> getAll() {
        return billDetailRepository.findAll()
                .stream()
                .map(billDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillDetailReadDTO getById(Long id) {
        return billDetailRepository.findById(id)
                .map(billDetailMapper::toDto)
                .orElseThrow(() -> new RuntimeException("BillDetail not found with id: " + id));
    }
}
