package edu.sena.petcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.condition.ConditionsDTO;
import edu.sena.petcare.services.conditions.ConditionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/conditions")
@RequiredArgsConstructor
public class ConditionsController {

    private final ConditionsService conditionsService;

    @PostMapping
    public ResponseEntity<ConditionsDTO> createCondition(@Valid @RequestBody ConditionsDTO conditionsDTO) {
        ConditionsDTO createdCondition = conditionsService.createCondition(conditionsDTO);
        return new ResponseEntity<>(createdCondition, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConditionsDTO>> getAllConditions() {
        List<ConditionsDTO> conditions = conditionsService.getAllConditions();
        return ResponseEntity.ok(conditions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ConditionsDTO>> getConditionById(@PathVariable Long id) {
        Optional<ConditionsDTO> condition = conditionsService.getConditionById(id);
        return ResponseEntity.ok(condition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConditionsDTO> updateCondition(@PathVariable Long id, @Valid @RequestBody ConditionsDTO conditionsDTO) {
        ConditionsDTO updatedCondition = conditionsService.updateCondition(id, conditionsDTO);
        return ResponseEntity.ok(updatedCondition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondition(@PathVariable Long id) {
        conditionsService.deleteCondition(id);
        return ResponseEntity.noContent().build();
    }
}
