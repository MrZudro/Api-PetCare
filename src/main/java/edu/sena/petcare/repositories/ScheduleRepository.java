package edu.sena.petcare.repositories;

import edu.sena.petcare.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByEmployee_Id(Long employeeId);

    List<Schedule> findByEmployee_IdAndDay(Long employeeId, String day);

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
        @Param("endTime") LocalTime endTime
    );
}