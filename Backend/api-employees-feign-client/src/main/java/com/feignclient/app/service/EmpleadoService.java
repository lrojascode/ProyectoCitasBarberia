package com.feignclient.app.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.feignclient.app.model.Empleado;

@FeignClient(name = "api-employees-feign-client", url = "http://localhost:8095/api/employees")
public interface EmpleadoService {
    
    @GetMapping
    ResponseEntity<Map<String, Object>> getEmpleados();

    @GetMapping("/{id}") 
    ResponseEntity<Map<String, Object>> getEmpleadosPorId(@PathVariable Long id);

    @PostMapping 
    ResponseEntity<Map<String, Object>> addEmpleados(@RequestBody Empleado empleado);

    @PutMapping("/{id}")
    ResponseEntity<Map<String, Object>> getEditarEmpleados(@RequestBody Empleado empleado, @PathVariable Long id);

    @DeleteMapping("/{id}") 
    ResponseEntity<Map<String, Object>> getEliminarEmpleados(@PathVariable Long id);

    @PutMapping("/eliminar/{id}")
    ResponseEntity<Map<String, Object>> getEliminarEmpleadosEnable(@PathVariable Long id);

    @GetMapping("/enable")
    ResponseEntity<Map<String, Object>> getListarEmpleadosEnable();
}

