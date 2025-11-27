package edu.sena.petcare.models;

import java.math.BigDecimal;
import java.util.List;

import edu.sena.petcare.models.enums.EmployeeCargo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@ToString(exclude = { "facturas" })
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Employee extends User {

    @Column(name = "employee_code", length = 50, unique = true)
    private String employeeCode; // Código de empleado interno

    @Column(name = "salary", precision = 10, scale = 2, nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private EmployeeCargo cargo;

    // relacion con Bill
    @OneToMany(mappedBy = "empleado")
    List<Bill> facturas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veterinary_clinic")
    private VeterinaryClinic veterinaryClinic;

    @Override
    public java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        var authorities = new java.util.ArrayList<org.springframework.security.core.GrantedAuthority>();
        authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority(getRole().name()));
        if (cargo != null) {
            authorities.add(
                    new org.springframework.security.core.authority.SimpleGrantedAuthority("CARGO_" + cargo.name()));
        }
        return authorities;
    }
}
