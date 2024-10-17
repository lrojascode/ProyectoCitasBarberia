package com.appointments.servicio_citas.serviceImplement;

import com.appointments.servicio_citas.model.Appointment;
import com.appointments.servicio_citas.repository.AppointmentsRepository;
import com.appointments.servicio_citas.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    @Autowired
    private AppointmentsRepository repository;

    @Override
    public ResponseEntity<Map<String, Object>> listarCitas() {
        Map<String, Object> response = new HashMap<>();
        List<Appointment> citas = repository.findAll();

        if (!citas.isEmpty()) {
            response.put("mensaje", "Lista de citas");
            response.put("citas", citas);
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("mensaje", "No existen citas registradas");
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerCitaPorId(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Appointment> cita = repository.findById(id);

        if (cita.isPresent()) {
            response.put("cita", cita.get());
            response.put("mensaje", "Cita encontrada");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("mensaje", "No se encontró la cita con ID: " + id);
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> crearCita(Appointment appointment) {
        Map<String, Object> response = new HashMap<>();
        repository.save(appointment);

        response.put("mensaje", "Cita creada con éxito");
        response.put("cita", appointment);
        response.put("status", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarCita(Long id, Appointment appointment) {
        Map<String, Object> response = new HashMap<>();
        Optional<Appointment> citaOpt = repository.findById(id);

        if (citaOpt.isPresent()) {
            Appointment citaExistente = citaOpt.get();
            citaExistente.setCustomerId(appointment.getCustomerId());
            citaExistente.setEmployeeId(appointment.getEmployeeId());
            citaExistente.setServiceId(appointment.getServiceId());
            citaExistente.setDatetime(appointment.getDatetime());
            citaExistente.setEndTime(appointment.getEndTime());
            repository.save(citaExistente);

            response.put("mensaje", "Cita actualizada con éxito");
            response.put("cita", citaExistente);
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("mensaje", "No se encontró la cita con ID: " + id);
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarCita(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Appointment> citaOpt = repository.findById(id);

        if (citaOpt.isPresent()) {
            repository.delete(citaOpt.get());
            response.put("mensaje", "Cita eliminada correctamente");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("mensaje", "No se encontró la cita con ID: " + id);
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
