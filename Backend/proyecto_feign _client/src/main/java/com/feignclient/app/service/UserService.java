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

import com.feignclient.app.model.User;

@FeignClient(name="proyecto-feign-client", url="http://localhost:8090/api/user")
public interface UserService {

	@GetMapping
	public ResponseEntity<Map<String, Object>> getUser();
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getUserPorId(@PathVariable Long id);
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user);
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> modifyUser(@RequestBody User user, @PathVariable Long id);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id);
}
