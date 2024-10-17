package com.appointments.servicio_citas.controller;

import com.appointments.servicio_citas.model.Appointment;
import com.appointments.servicio_citas.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentsService service;

    // Listar todas las citas
    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        return service.listarCitas();
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Long id) {
        return service.obtenerCitaPorId(id);
    }

    // Crear una nueva cita
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@Valid @RequestBody Appointment appointment) {
        return service.crearCita(appointment);
    }

    // Actualizar una cita existente
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @Valid @RequestBody Appointment appointment) {
        return service.actualizarCita(id, appointment);
    }

    // Eliminar una cita por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        return service.eliminarCita(id);
    }
}

