package com.appointments.servicio_citas.repository;

import com.appointments.servicio_citas.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
}
