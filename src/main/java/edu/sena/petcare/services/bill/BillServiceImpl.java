package edu.sena.petcare.services.bill;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.bill.BillReadDTO;
import edu.sena.petcare.mapper.BillMapper;
import edu.sena.petcare.repositories.BillRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public List<BillReadDTO> getAll() {
        return billRepository.findAll()
                .stream()
                .map(billMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BillReadDTO getById(Long id) {
        return billRepository.findById(id)
                .map(billMapper::toReadDTO)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
    }
}
