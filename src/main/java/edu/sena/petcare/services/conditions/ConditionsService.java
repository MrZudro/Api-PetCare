package edu.sena.petcare.services.conditions;

import java.util.List;
import java.util.Optional;

import edu.sena.petcare.dto.condition.ConditionsDTO;

public interface ConditionsService {

    ConditionsDTO createCondition(ConditionsDTO conditionsDTO);
    List<ConditionsDTO> getAllConditions();
    Optional<ConditionsDTO> getConditionById(Long id);
    ConditionsDTO updateCondition(Long id, ConditionsDTO conditionsDTO);
    void deleteCondition(Long id);
}
