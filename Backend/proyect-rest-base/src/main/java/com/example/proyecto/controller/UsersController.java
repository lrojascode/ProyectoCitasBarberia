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

import com.example.proyecto.model.users;
import com.example.proyecto.service.UsersService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
	@Autowired
	private UsersService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> allUsers(){
		return service.listarUsuarios();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarPorID(@PathVariable Long id){
		return service.listarUsuariosPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@RequestBody users user){
		return service.agregarUsuarios(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody users user, @PathVariable Long id){
		return service.editarUsuarios(user, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		return service.eliminarUsuarios(id);
	} 
}
