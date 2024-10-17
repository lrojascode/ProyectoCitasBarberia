package pe.edu.cibertec.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import pe.edu.cibertec.model.Servicio;

public interface ServicioService {
	
	public ResponseEntity<Map<String, Object>> listarServicios();
	
	public ResponseEntity<Map<String, Object>> obtenerServicio(Long id);
	
	public ResponseEntity<Map<String, Object>> registrarServicio(Servicio servicio);
	
	public ResponseEntity<Map<String, Object>> editarServicio(Servicio servicio, Long id);
	
	public ResponseEntity<Map<String, Object>> deshabilitarServicio(Long id);

}
