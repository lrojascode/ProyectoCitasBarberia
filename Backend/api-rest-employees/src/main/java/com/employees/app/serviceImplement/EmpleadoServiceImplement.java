package com.employees.app.serviceImplement;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employees.app.model.Empleado;
import com.employees.app.repository.EmpleadoRepository;
import com.employees.app.service.EmpleadoService;
import java.util.Base64;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {

	@Autowired
	private EmpleadoRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleados() {
		Map<String, Object> response = new HashMap<>();
		List<Empleado> empleados = dao.findAll();
		
		if(!empleados.isEmpty()) {
			response.put("mensaje", "Lista de Empleados");
			response.put("empleados", empleados);
			response.put("status", HttpStatus.OK);
			response.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.put("mensaje", "No existen registros");
			response.put("status", HttpStatus.NOT_FOUND);
			response.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleadosPorId(Long id) {
		Map<String,Object> respuesta = new HashMap<>();		
		Optional<Empleado> empleados = dao.findById(id);
		
		if(empleados.isPresent()) {
			respuesta.put("productos", empleados);
			respuesta.put("mensaje", "Busqueda correcta");
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.ok().body(respuesta);	
		}else {
			respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> actualizarEmpleados(Empleado emp, Long id) {
	    Map<String, Object> response = new HashMap<>();
	    Optional<Empleado> empleadoOpt = dao.findById(id);
	    
	    if (empleadoOpt.isPresent()) {
	        Empleado empleado = empleadoOpt.get();
	        
	        if (emp.getFirst_name() != null) empleado.setFirst_name(emp.getFirst_name());
	        if (emp.getLast_name() != null) empleado.setLast_name(emp.getLast_name());
	        if (emp.getProfession() != null) empleado.setProfession(emp.getProfession());
	        if (emp.getPicture() != null) empleado.setPicture(emp.getPicture());
	        if (emp.getWorking_days() != null) empleado.setWorking_days(emp.getWorking_days());
	        if (emp.getActive() != null) empleado.setActive(emp.getActive());

	        dao.save(empleado);
	        response.put("empleados", empleado);
	        response.put("mensaje", "Los datos del empleado han sido modificados");
	        response.put("status", HttpStatus.CREATED);
	        response.put("fecha", new Date());	

	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    } 
	    
	    response.put("mensaje", "No existe el empleado con ID: " + id);
	    response.put("status", HttpStatus.NOT_FOUND);
	    response.put("fecha", new Date());
	    
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}


	@Override
	public ResponseEntity<Map<String, Object>> eliminarEmpleadosLogico(Long id) {
	    Map<String, Object> response = new HashMap<>();
	    Optional<Empleado> empleadoX = dao.findById(id);
	    
	    if (empleadoX.isPresent()) {
	        Empleado empleado = empleadoX.get();
	        empleado.setActive(false); 
	        dao.save(empleado);
	        
	        response.put("mensaje", "Se ha eliminado correctamente");
	        response.put("status", HttpStatus.OK);
	        response.put("fecha", new Date());
	        
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } else {
	        response.put("mensaje", "No existe el empleado con ID: " + id);
	        response.put("status", HttpStatus.NOT_FOUND);
	        response.put("fecha", new Date());
	        
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> agregarEmpleados(Empleado empleado) {
	    Map<String, Object> respuesta = new HashMap<>();

	    if (empleado.getPictureBase64() != null && empleado.getPictureBase64().startsWith("data:image/")) {
	        String base64Image = empleado.getPictureBase64().split(",")[1];
	        empleado.setPicture(Base64.getDecoder().decode(base64Image));
	    }

	    if (empleado.getId() != null && dao.existsById(empleado.getId())) {
	        respuesta.put("mensaje", "Error: Ya existe un empleado con el ID proporcionado.");
	        respuesta.put("status", HttpStatus.BAD_REQUEST);
	        respuesta.put("fecha", new Date());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	    }

	    dao.save(empleado);
	    respuesta.put("empleados", empleado);
	    respuesta.put("mensaje", "Se añadió correctamente el empleado.");
	    respuesta.put("status", HttpStatus.CREATED);
	    respuesta.put("fecha", new Date());

	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

	
	@Override
	public ResponseEntity<Map<String, Object>> eliminarEmpleados(Long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Empleado> empleadoExiste = dao.findById(id);	
		if (empleadoExiste.isPresent()) {
			Empleado empleado = empleadoExiste.get();
			dao.delete(empleado);
			respuesta.put("mensaje", "Eliminado correctamente");
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	

			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}	
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleadosEnable() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Empleado> empleados = dao.findAllByActive(true);

		if (!empleados.isEmpty()) {
			respuesta.put("mensaje", "Lista de productos");
			respuesta.put("empleados", empleados);
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());

			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errores = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errores.put(error.getField(), error.getDefaultMessage());
	    });
	    return ResponseEntity.badRequest().body(errores);
	}
	
}
