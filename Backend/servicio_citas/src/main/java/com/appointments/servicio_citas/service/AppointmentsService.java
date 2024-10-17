package com.appointments.servicio_citas.service;

import com.appointments.servicio_citas.model.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AppointmentsService {
    ResponseEntity<Map<String, Object>> listarCitas();
    ResponseEntity<Map<String, Object>> obtenerCitaPorId(Long id);
    ResponseEntity<Map<String, Object>> crearCita(Appointment appointment);
    ResponseEntity<Map<String, Object>> actualizarCita(Long id, Appointment appointment);
    ResponseEntity<Map<String, Object>> eliminarCita(Long id);
}

