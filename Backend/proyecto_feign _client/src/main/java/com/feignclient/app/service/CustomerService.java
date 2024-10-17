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

import com.feignclient.app.model.Customer;

@FeignClient(name="proyecto-feign-client", url="http://localhost:8090/api/customer")
public interface CustomerService {

	@GetMapping
	public ResponseEntity<Map<String, Object>> getCustomer();
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getCustomerPorId(@PathVariable Long id);
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody Customer customer);
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> modifyCustomer(@RequestBody Customer customer, @PathVariable Long id);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable Long id);
}
