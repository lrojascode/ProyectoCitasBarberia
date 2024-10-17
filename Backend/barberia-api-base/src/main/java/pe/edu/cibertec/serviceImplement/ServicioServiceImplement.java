package pe.edu.cibertec.serviceImplement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.model.Servicio;
import pe.edu.cibertec.repository.ServicioRepository;
import pe.edu.cibertec.service.ServicioService;

@Service
public class ServicioServiceImplement implements ServicioService{

	@Autowired
	private ServicioRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listarServicios() {
		
		Map<String, Object> respuesta = new HashMap<>();
		List<Servicio> servicios = dao.findAll();
		
		if(!servicios.isEmpty()) {
			respuesta.put("mensaje","Listado de servicios");
			respuesta.put("servicios", servicios);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje","No existe registros de servicios");
			respuesta.put("servicios", null);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> obtenerServicio(Long id) {
		
		Map<String, Object> respuesta = new HashMap<>();
		Optional<Servicio> servicios = dao.findById(id);
		
		if(servicios.isPresent()) {
			respuesta.put("mensaje","Listado de servicios");
			respuesta.put("servicios", servicios);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje","No existe registros de servicios");
			respuesta.put("servicios", null);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> registrarServicio(Servicio servicio) {
		
		Map<String, Object> respuesta = new HashMap<>();
		dao.save(servicio);
		respuesta.put("servicios", servicio);
		respuesta.put("mensaje", "Se guardó correctamente.");
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> editarServicio(Servicio serv, Long id) {
		
		Map<String, Object> respuesta = new HashMap<>();
		Optional<Servicio> servicios = dao.findById(id);
		
		if(servicios.isPresent()) {
			Servicio servicio = servicios.get();
			servicio.setDescription(serv.getDescription());
			servicio.setDuration_minutes(serv.getDuration_minutes());
			servicio.setName(serv.getName());
			servicio.setPrice(serv.getPrice());
			respuesta.put("mensaje","Listado de servicios");
			respuesta.put("servicios", servicios);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje","No existe registros de servicios");
			respuesta.put("servicios", null);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> deshabilitarServicio(Long id) {
		
		Map<String, Object> respuesta = new HashMap<>();
		Optional<Servicio> servicios = dao.findById(id);
		
		if(servicios.isPresent()) {
			Servicio servicio = servicios.get();
			dao.delete(servicio);
			respuesta.put("mensaje","Eliminado correctamente");
			respuesta.put("status", HttpStatus.NO_CONTENT);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
		}else {
			respuesta.put("mensaje","No existe registros de servicios");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}

	
	
}
