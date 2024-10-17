package com.example.proyecto.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.model.customers;
import com.example.proyecto.service.CustomersService;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomersController {
	@Autowired
	private CustomersService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> allCustomers(){
		return service.listarCustomers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarPorID(@PathVariable Long id){
		return service.listarCustomersPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@RequestBody customers customer){
		return service.agregarCustomers(customer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody customers customer, @PathVariable Long id){
		return service.editarCustomers(customer, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		return service.eliminarCustomers(id);
	} 
}
