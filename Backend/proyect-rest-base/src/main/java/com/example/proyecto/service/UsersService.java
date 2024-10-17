package com.example.proyecto.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.proyecto.model.users;


public interface UsersService {
	
	public ResponseEntity<Map<String, Object>> listarUsuarios();
	
	public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Long id);
	
	public ResponseEntity<Map<String, Object>> agregarUsuarios(users user);

	public ResponseEntity<Map<String, Object>> editarUsuarios(users user, Long id);
	
	public ResponseEntity<Map<String, Object>> eliminarUsuarios(Long id);
}
