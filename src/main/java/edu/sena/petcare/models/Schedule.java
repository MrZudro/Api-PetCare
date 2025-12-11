package edu.sena.petcare.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "schedules", indexes = {
        @Index(name = "idx_schedules_employee_day", columnList = "id_employee, day"),
        @Index(name = "idx_schedules_employee_date", columnList = "id_employee, schedule_date"),
        @Index(name = "idx_schedules_period", columnList = "period_start_date, period_end_date")
})
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 20, nullable = false)
    private String day; // Día de la semana (Lunes, Martes, etc.) - mantener compatibilidad

    @Column(name = "schedule_date")
    private LocalDate scheduleDate; // Fecha específica del horario

    @Column(name = "start_time", nullable = false)
    private LocalTime start_time;

    @Column(name = "end_time", nullable = false)
    private LocalTime end_time;

    @Column(name = "is_overtime", nullable = false)
    private Boolean isOvertime = false; // Indica si son horas extras

    @Column(name = "period_start_date")
    private LocalDate periodStartDate; // Inicio del período quincenal

    @Column(name = "period_end_date")
    private LocalDate periodEndDate; // Fin del período quincenal

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;
}