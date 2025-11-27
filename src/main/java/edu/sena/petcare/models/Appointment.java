package edu.sena.petcare.models;

import java.time.LocalDateTime;

import edu.sena.petcare.models.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "customer", "veterinaryClinic", "employee", "service" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // Relación con el cliente que solicita la cita
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    // Relación con la clínica o sede de la cita
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veterinary", nullable = false)
    private VeterinaryClinic veterinaryClinic;

    // Relación con el empleado (veterinario) asignado (Puede ser null inicialmente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    // Relación con el servicio solicitado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service", nullable = false)
    private Services service;

    @Column(name = "appointment_date_time", nullable = false)
    private LocalDateTime appointmentDateTime; // La fecha y hora exactas de la cita.

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private AppointmentStatus status; // Estado de la cita (PENDING, CONFIRMED, CANCELLED, COMPLETED, UNATTENDED)

    @Column(name = "reason", length = 500)
    private String reason; // Razón de la cita
}