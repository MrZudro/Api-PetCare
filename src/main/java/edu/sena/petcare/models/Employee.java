package edu.sena.petcare.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@ToString(exclude = {"facturas"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Employee extends User{

    @Column(name = "employee_code", length = 50, unique = true)
    private String employeeCode; // Código de empleado interno

    @Column(name = "salary", precision = 10, scale = 2, nullable = false)
    private BigDecimal salary;

    @Column(length = 150, nullable = false)
    private String cargo;

    //relacion con Bill
    @OneToMany(mappedBy = "empleado")
    private List<Bill> facturas;

}
