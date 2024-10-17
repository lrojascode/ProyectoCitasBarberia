package com.feignclient.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.feignclient.app.model.Empleado;
import com.feignclient.app.service.EmpleadoService;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/employees/feignclient")
public class EmpleadoController {

	@Autowired
	private EmpleadoService service;

	// Listar
	@GetMapping()
	public ResponseEntity<Map<String, Object>> allProducto() {
		return service.getEmpleados();
	}

	// Listar por id
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> allEmpleadoPorId(@PathVariable Long id) {
		return service.getEmpleadosPorId(id);
	}

	// Agregar
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@RequestBody Empleado empleado) {
		return service.addEmpleados(empleado);
	}

	// Editar
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody Empleado empleado, @PathVariable Long id) {
		return service.getEditarEmpleados(empleado, id);
	}

	// Eliminar fisico
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
		return service.getEliminarEmpleados(id);
	}

	// Eliminar Logico
	@PutMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> eliminarPorEnable(@PathVariable Long id) {
		return service.getEliminarEmpleadosEnable(id);
	}

	// Mostrar todo cuando enable = "S"
	@GetMapping("/enable")
	public ResponseEntity<Map<String, Object>> listaPorEnable() {
		return service.getListarEmpleadosEnable();
	}

	@GetMapping("/gson")
	public ResponseEntity<Map<String, Object>> obtenerEmpleadoJson() {
		try {
			Map<String, Object> body = service.getEmpleados().getBody();
			List<Map<String, Object>> empleados = new ArrayList<>();

			if (body != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.valueToTree(body);
				String estado = jsonNode.get("status").asText();
				JsonNode jsonNodeEmpleados = jsonNode.path("empleados");

				for (JsonNode nodo : jsonNodeEmpleados) {
					Empleado e = new Empleado();
					e.setId(nodo.get("id").asLong());
					e.setFirst_name(nodo.has("first_name") ? nodo.get("first_name").asText() : "Sin nombre");
					e.setLast_name(nodo.has("last_name") ? nodo.get("last_name").asText() : "Sin apellido");
					e.setProfession(nodo.has("profession") ? nodo.get("profession").asText() : "Sin profesion");

					if (nodo.has("picture") && !nodo.get("picture").isNull()) {
						String pictureBase64 = nodo.get("picture").asText();
						e.setPictureBase64(pictureBase64);
						e.setPicture(Base64.getDecoder().decode(pictureBase64));
					} else {
						e.setPictureBase64(null);
						e.setPicture(new byte[0]); 
					}

					e.setWorking_days(nodo.has("working_days") ? nodo.get("working_days").asText() : "Sin horario");
					e.setActive(nodo.has("active") ? nodo.get("active").asBoolean() : false);

					Map<String, Object> empleadoData = new LinkedHashMap<>();
					empleadoData.put("id", e.getId());
					empleadoData.put("first_name", e.getFirst_name());
					empleadoData.put("last_name", e.getLast_name());
					empleadoData.put("profession", e.getProfession());
					empleadoData.put("pictureBase64", e.getPictureBase64());
					empleadoData.put("working_days", e.getWorking_days());
					empleadoData.put("active", e.getActive());

					empleados.add(empleadoData);
				}

				Map<String, Object> response = new LinkedHashMap<>();
				response.put("status", estado);
				response.put("fecha", LocalDate.now());
				response.put("empleados", empleados);

				return ResponseEntity.ok(response);
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("mensaje", "Error al obtener empleados: cuerpo vacío"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("mensaje", "Error al procesar empleados: " + e.getMessage()));
		}
	}

}