package com.barbershop.serviceImplement;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barbershop.model.Customer;
import com.barbershop.service.CustomerService;

@Service
public class CustomerServiceImplement implements CustomerService {

	
	@Override
	public ResponseEntity<Map<String, Object>> listarClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarClientesPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> agregarCliente(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> editarCliente(Customer customer, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarCliente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarClientesEnable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarClientesEnable(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
