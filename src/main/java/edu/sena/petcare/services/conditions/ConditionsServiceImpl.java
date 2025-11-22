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
public class ConditionsServiceImpl implements ConditionsService {

    private final ConditionsRepository conditionsRepository;
    private final ConditionsMapper conditionsMapper;

    @Override
    public ConditionsDTO createCondition(ConditionsDTO conditionsDTO) {
        if (conditionsDTO == null) {
            throw new IllegalArgumentException("conditionsDTO cannot be null");
        }
        Conditions conditions = conditionsMapper.toEntity(conditionsDTO);
        @SuppressWarnings("null")
        Conditions savedConditions = conditionsRepository.save(conditions);
        return conditionsMapper.toDTO(savedConditions);
    }

    @Override
    public List<ConditionsDTO> getAllConditions() {
        List<Conditions> conditions = conditionsRepository.findAll();
        return conditionsMapper.toDtoList(conditions);
    }

    @Override
    public Optional<ConditionsDTO> getConditionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        Conditions conditions = conditionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condición con id " + id + " no encontrada"));
        return Optional.ofNullable(conditionsMapper.toDTO(conditions));
    }

    @Override
    public ConditionsDTO updateCondition(Long id, ConditionsDTO conditionsDTO) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (conditionsDTO == null) {
            throw new IllegalArgumentException("conditionsDTO cannot be null");
        }
        Conditions conditions = conditionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condición con id " + id + " no encontrada"));
        conditionsMapper.updateEntity(conditionsDTO, conditions);
        @SuppressWarnings("null")
        Conditions updatedConditions = conditionsRepository.save(conditions);
        return conditionsMapper.toDTO(updatedConditions);
    }

    @Override
    public void deleteCondition(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (!conditionsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Condición con id " + id + " no encontrada");
        }
        conditionsRepository.deleteById(id);
    }
}
