package pe.edu.cibertec.feign.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient( name = "barberia-feign-client", url="http://localhost:8190/api/servicios" )
public interface ServicioService {

	@GetMapping
	public ResponseEntity<Map<String, Object>> getServicios();
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getServiciosFindByID(Long id);
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> setServicio(Long id);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteServicio(Long id);
	
}
