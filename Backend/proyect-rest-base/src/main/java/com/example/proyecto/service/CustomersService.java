package com.example.proyecto.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.proyecto.model.customers;

public interface CustomersService {
	
	public ResponseEntity<Map<String, Object>> listarCustomers();
	
	public ResponseEntity<Map<String, Object>> listarCustomersPorId(Long id);
	
	public ResponseEntity<Map<String, Object>> agregarCustomers(customers customer);

	public ResponseEntity<Map<String, Object>> editarCustomers(customers customer, Long id);
	
	public ResponseEntity<Map<String, Object>> eliminarCustomers(Long id);
}
