package pe.edu.cibertec.feign.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.feign.service.ServicioService;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

	@Autowired
	private ServicioService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listarEServicios(){
		return service.getServicios();
	}
	
}
