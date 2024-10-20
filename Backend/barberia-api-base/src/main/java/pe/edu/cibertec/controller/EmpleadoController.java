package pe.edu.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.dto.EmpleadoDTO;
import pe.edu.cibertec.service.EmpleadoService;

@RestController
@RequestMapping("/api/employees")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/{employeeId}/services")
    public ResponseEntity<EmpleadoDTO> getServicesByEmployeeId(@PathVariable Long employeeId) {
        EmpleadoDTO empleado = empleadoService.getEmployeeServices(employeeId);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getById(@PathVariable Long id) {
        try {
            EmpleadoDTO empleado = empleadoService.getById(id);
            return ResponseEntity.ok(empleado);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
