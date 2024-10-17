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
import com.feignclient.app.model.User;
import com.feignclient.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> allUser(){
		return service.getUser();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> allUserById(@PathVariable Long id){
		return service.getUserPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user){
		return service.addUser(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> modificarUser(@RequestBody User user, @PathVariable Long id){
		return service.modifyUser(user, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminarUser(@PathVariable Long id){
		return service.deleteUser(id);
	}
	
	@GetMapping("/gson")
	public List<User> obtenerUserJson(){
		Map<String, Object> body = service.getUser().getBody();
		List<User> users = new ArrayList<>();
		
		if(body != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.valueToTree(body);
			
			//String mensaje = jsonNode.get("mensaje").asText();
			
			JsonNode jsonNodeUser = jsonNode.path("user");
			
			for(JsonNode nodo : jsonNodeUser) {
				User u = new User();
				u.setId(nodo.get("id").asLong());
				u.setEmail(nodo.get("email").asText());
				u.setPassword(nodo.get("password").asText());
				u.setUsername(nodo.get("username").asText());
				users.add(u);
			}
		}
		return users;
	}
}
