package com.employees.app.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.employees.app.model.Empleado;

public interface EmpleadoService {

	public ResponseEntity<Map<String, Object>> listarEmpleados();
	
	public ResponseEntity<Map<String, Object>> listarEmpleadosPorId(Long id);

	public ResponseEntity<Map<String, Object>> agregarEmpleados(@Valid Empleado empleado);

	public ResponseEntity<Map<String, Object>> actualizarEmpleados(Empleado empleado, Long id);

	public ResponseEntity<Map<String, Object>> eliminarEmpleadosLogico(Long id);

	public ResponseEntity<Map<String, Object>> eliminarEmpleados(Long id);

	public ResponseEntity<Map<String, Object>> listarEmpleadosEnable();



}
