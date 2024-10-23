package com.barbershop.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.barbershop.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

	@Query("SELECT c FROM Cita c WHERE c.employeeId = :employeeId AND c.datetime BETWEEN :startDatetime AND :endDatetime AND c.cancelled = false")
	List<Cita> findByEmployeeIdAndDatetimeBetween(@Param("employeeId") Long employeeId, @Param("startDatetime") LocalDateTime startDatetime, @Param("endDatetime") LocalDateTime endDatetime);
    
    List<Cita> findByCustomerId(Long customerId);
    
    @Modifying
    @Transactional
    @Query("UPDATE Cita c SET c.cancelled = true WHERE c.employeeId = :empleadoId AND c.datetime = :fechaHora")
    void cancelCita(@Param("empleadoId") Long empleadoId, @Param("fechaHora") LocalDateTime fechaHora);

    // Liberar el horario del empleado
    @Modifying 
    @Transactional
    @Query("UPDATE EmployeeSchedule es SET es.available = true WHERE es.empleado.id = :empleadoId AND es.fromHour <= :hora AND es.toHour >= :hora")
    void releaseEmployeeSchedule(@Param("empleadoId") Long empleadoId, @Param("hora") LocalTime hora);

}