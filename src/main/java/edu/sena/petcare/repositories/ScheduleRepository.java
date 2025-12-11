package edu.sena.petcare.repositories;

import edu.sena.petcare.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByEmployee_Id(Long employeeId);

    List<Schedule> findByEmployee_IdAndDay(Long employeeId, String day);

    // Buscar horarios por empleado y rango de fechas
    @Query("""
                SELECT s FROM Schedule s
                WHERE s.employee.id = :employeeId
                  AND s.scheduleDate BETWEEN :startDate AND :endDate
                ORDER BY s.scheduleDate, s.start_time
            """)
    List<Schedule> findByEmployeeAndDateRange(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Buscar todos los horarios en un rango de fechas (para vista de calendario)
    @Query("""
                SELECT s FROM Schedule s
                WHERE s.scheduleDate BETWEEN :startDate AND :endDate
                ORDER BY s.scheduleDate, s.start_time
            """)
    List<Schedule> findByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("""
                SELECT s FROM Schedule s
                WHERE s.employee.id = :employeeId
                  AND s.day = :day
                  AND (
                      (:startTime < s.end_time AND :endTime > s.start_time)
                  )
            """)
    List<Schedule> findOverlapping(
            @Param("employeeId") Long employeeId,
            @Param("day") String day,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    // Validar solapamiento por fecha específica
    @Query("""
                SELECT s FROM Schedule s
                WHERE s.employee.id = :employeeId
                  AND s.scheduleDate = :scheduleDate
                  AND (
                      (:startTime < s.end_time AND :endTime > s.start_time)
                  )
            """)
    List<Schedule> findOverlappingByDate(
            @Param("employeeId") Long employeeId,
            @Param("scheduleDate") LocalDate scheduleDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);
}