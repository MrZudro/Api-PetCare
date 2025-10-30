package edu.sena.petcare.services.conditions;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.condition.ConditionsDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.ConditionsMapper;
import edu.sena.petcare.models.Conditions;
import edu.sena.petcare.repositories.ConditionsRepository;
import lombok.*;

@Service
@RequiredArgsConstructor
public class ConditionsServiceImpl implements ConditionsService{

    private final ConditionsRepository conditionsRepository;

    @Override
    public ConditionsDTO createCondition(ConditionsDTO conditionsDTO) {
        Conditions conditions = ConditionsMapper.toEntity(conditionsDTO);
        Conditions savedConditions = conditionsRepository.save(conditions);
        return ConditionsMapper.toDTO(savedConditions);
    }

    @Override
    public List<ConditionsDTO> getAllConditions() {
        List<Conditions> conditions = conditionsRepository.findAll();
        return conditions.stream()
                .map(ConditionsMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ConditionsDTO> getConditionById(Long id) {
        Conditions conditions = conditionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condición con id " + id + " no encontrada"));
        return Optional.of(ConditionsMapper.toDTO(conditions));
    }

    @Override
    public ConditionsDTO updateCondition(Long id, ConditionsDTO conditionsDTO) {
        Conditions conditions = conditionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condición con id " + id + " no encontrada"));
        ConditionsMapper.updateEntity(conditionsDTO, conditions);
        Conditions updatedConditions = conditionsRepository.save(conditions);
        return ConditionsMapper.toDTO(updatedConditions);
    }

    @Override
    public void deleteCondition(Long id) {
        conditionsRepository.deleteById(id);
    }
}
