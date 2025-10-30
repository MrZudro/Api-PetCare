package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}


