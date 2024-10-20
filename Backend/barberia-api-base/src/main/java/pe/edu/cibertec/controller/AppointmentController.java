package pe.edu.cibertec.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.dto.AppointmentDTO;
import pe.edu.cibertec.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AppointmentDTO>> listAllByCustomerId(@PathVariable Long customerId) {
        List<AppointmentDTO> appointments = appointmentService.listAllByCustomerId(customerId);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelById(id);
        return ResponseEntity.ok().build();
    }


    /**
     * Allows to get the schedules of free appointments of an employee by its ID and the date
     *
     * @param employeeId ID of employee
     * @param serviceId  ID of service
     * @param date       Date of the query
     * @return List of LocalTime
     */
    @GetMapping("/employee/{employee_id}/service/{service_id}")
public ResponseEntity<List<AppointmentDTO>> getOccupiedAppointments(
        @PathVariable("employee_id") Long employeeId,
        @PathVariable("service_id") Long serviceId,
        @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
    List<AppointmentDTO> occupiedAppointments = appointmentService.listFreeAppointmentsByEmployeeIdAndServiceIdAndDate(employeeId, serviceId, date);
    return new ResponseEntity<>(occupiedAppointments, HttpStatus.OK);
}


}
