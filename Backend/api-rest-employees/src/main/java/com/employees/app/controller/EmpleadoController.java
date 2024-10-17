package com.employees.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.app.model.Empleado;
import com.employees.app.service.EmpleadoService;


@RestController
@RequestMapping("/api/employees")
public class EmpleadoController {

	@Autowired
	private EmpleadoService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listar() {
		return service.listarEmpleados();
	}
	
	@GetMapping("/enable")
	public ResponseEntity<Map<String, Object>> listarPorEnable(){
		return service.listarEmpleadosEnable();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id){
		return service.listarEmpleadosPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@Valid @RequestBody Empleado empleado){
		return service.agregarEmpleados(empleado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> actualizar(@RequestBody Empleado empleado,@PathVariable Long id) {
		return service.actualizarEmpleados(empleado,id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		return service.eliminarEmpleados(id);
	}
	
	@PutMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> eliminarLogico(@PathVariable Long id) {
		return service.eliminarEmpleadosLogico(id);
	}
}