package com.feignclient.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feignclient.app.model.Customer;
import com.feignclient.app.model.User;
import com.feignclient.app.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> allCustomer(){
		return service.getCustomer();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> allCustomerById(@PathVariable Long id){
		return service.getCustomerPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody Customer user){
		return service.addCustomer(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> modificarCustomer(@RequestBody Customer customer, @PathVariable Long id){
		return service.modifyCustomer(customer, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminarCustomer(@PathVariable Long id){
		return service.deleteCustomer(id);
	}
	
	@GetMapping("/gson")
	public List<Customer> obtenerCustomerJson(){
		Map<String, Object> body = service.getCustomer().getBody();
		List<Customer> customer = new ArrayList<>();
		
		if(body != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.valueToTree(body);
			
			JsonNode jsonNodeUser = jsonNode.path("customer");
			
			for(JsonNode nodo : jsonNodeUser) {
				Customer c = new Customer();
				c.setId(nodo.get("id").asLong());
				c.setUsers_id(nodo.get("user_id").asLong());
				c.setFirst_name(nodo.get("First_name").asText());
				c.setLast_name(nodo.get("last_name").asText());
				c.setPhone(nodo.get("phone").asText());
				customer.add(c);
			}
		}
		return customer;
	}
}
