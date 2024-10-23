package com.barbershop.serviceImplement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.barbershop.dao.ReservaTurnoRequest;
import com.barbershop.model.Cita;
import com.barbershop.model.Customer;
import com.barbershop.model.Empleado;
import com.barbershop.model.EmployeeSchedule;
import com.barbershop.model.Servicio;
import com.barbershop.repository.CitaRepository;
import com.barbershop.repository.CustomerRepository;
import com.barbershop.repository.EmpleadoRepository;
import com.barbershop.repository.EmployeeScheduleRepository;
import com.barbershop.repository.ServiceRepository;
import com.barbershop.service.CitaService;

@Service
public class CitaServiceImplement implements CitaService{

	@Autowired
	private CitaRepository citaDao;
	
    @Autowired
    private EmployeeScheduleRepository employeeScheduleDao;
    
    @Autowired
    private EmpleadoRepository empleadoDao;
    
    @Autowired 
    private ServiceRepository serviceDao;
    
    @Autowired
    private CustomerRepository customerDao;
	
	@Override
    public ResponseEntity<Map<String, Object>> listarCitas() {
        Map<String, Object> response = new HashMap<>();
        List<Cita> citas = citaDao.findAll();

        if (!citas.isEmpty()) {
            response.put("mensaje", "Lista de citas");
            response.put("citas", citas);
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("mensaje", "No existen citas registradas");
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
	@Override
    public ResponseEntity<Map<String, Object>> obtenerCitaPorId(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Cita> cita = citaDao.findById(id);

        if (cita.isPresent()) {
            response.put("cita", cita.get());
            response.put("mensaje", "Cita encontrada");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("mensaje", "No se encontró la cita con ID: " + id);
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
	@Override
    public ResponseEntity<Map<String, Object>> crearCita(Cita cita) {
        Map<String, Object> response = new HashMap<>();
        citaDao.save(cita);

        response.put("mensaje", "Cita creada con éxito");
        response.put("cita", cita);
        response.put("status", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
	@Override
	public ResponseEntity<Map<String, Object>> actualizarCita(Long id, Cita cita) {
	    Map<String, Object> response = new HashMap<>();
	    Optional<Cita> citaOpt = citaDao.findById(id);

	    if (citaOpt.isPresent()) {
	        Cita citaExistente = citaOpt.get();

	        // Verificar si los IDs no son nulos
	        if (cita.getCustomerId() == null || cita.getEmployeeId() == null || cita.getServiceId() == null) {
	            response.put("mensaje", "Los IDs de cliente, empleado y servicio no pueden ser nulos");
	            response.put("status", HttpStatus.BAD_REQUEST);
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }

	        // Asignar los IDs a la cita existente
	        citaExistente.setCustomerId(cita.getCustomerId());
	        citaExistente.setEmployeeId(cita.getEmployeeId());
	        citaExistente.setServiceId(cita.getServiceId());

	        // Asignar los otros campos
	        citaExistente.setDatetime(cita.getDatetime());
	        citaExistente.setEndTime(cita.getEndTime());
	        citaExistente.setCancelled(cita.getCancelled());

	        // Guardar la cita actualizada
	        citaDao.save(citaExistente);

	        // Preparar la respuesta
	        response.put("mensaje", "Cita actualizada con éxito");
	        response.put("cita", citaExistente);
	        response.put("status", HttpStatus.OK);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } else {
	        // Si no se encuentra la cita, devolver un error 404
	        response.put("mensaje", "No se encontró la cita con ID: " + id);
	        response.put("status", HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}
	
	@Override
    public ResponseEntity<Map<String, Object>> eliminarCita(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Cita> citaOpt = citaDao.findById(id);

        if (citaOpt.isPresent()) {
        	citaDao.delete(citaOpt.get());
            response.put("mensaje", "Cita eliminada correctamente");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("mensaje", "No se encontró la cita con ID: " + id);
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
	
	@Override
	public ResponseEntity<Map<String, Object>> listarCitasPorCustomer(Authentication authentication) {
	    // Obtener el email del token
	    String email = authentication.getName();

	    // Buscar el Customer por email
	    Customer customer = customerDao.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Customer no encontrado"));

	    Long customerId = customer.getId(); // Obtener el ID del Customer

	    // Obtener las citas del Customer
	    List<Cita> citas = citaDao.findByCustomerId(customerId);

	    // Crear la respuesta personalizada
	    List<Map<String, Object>> citasResponse = new ArrayList<>();
	    for (Cita cita : citas) {
	        Map<String, Object> citaDetails = new HashMap<>();

	        // Formatear el horario con AM/PM
	        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("hh:mm a");
	        String horarioFormateado = cita.getDatetime().format(horaFormatter).toUpperCase();
	        citaDetails.put("Horario", horarioFormateado);

	        // Formatear la fecha
	        citaDetails.put("Fecha", formatFecha(cita.getDatetime()));

	        // Obtener el servicio por ID y agregar el nombre
	        Servicio servicio = serviceDao.findById(cita.getServiceId())
	                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
	        citaDetails.put("Servicio", servicio.getName()); // Agregar el nombre del servicio

	        // Obtener la duración del servicio
	        long duracionMinutos = servicio.getDuration_minutes();
	        citaDetails.put("Duración", duracionMinutos + " minutos");

	        // Verificar si la cita fue cancelada y agregar estado si es necesario
	        if (cita.getCancelled()) {
	            citaDetails.put("estado", "CANCELADA");
	        }

	        citasResponse.add(citaDetails);
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("mensaje", "Resumen de Citas");
	    response.put("citas", citasResponse);
	    response.put("status", "OK");

	    return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarHorariosDisponibles(Long empleadoId, LocalDate fecha) {
	    // Obtener los horarios de trabajo del empleado
	    List<EmployeeSchedule> horarios = employeeScheduleDao.findByEmpleadoId(empleadoId);

	    // Crear los límites del día para la consulta
	    LocalDateTime startOfDay = fecha.atStartOfDay();
	    LocalDateTime endOfDay = fecha.atTime(LocalTime.MAX);

	    // Obtener las citas ya reservadas (y no canceladas) para el empleado en la fecha seleccionada
	    List<Cita> citasReservadas = citaDao.findByEmployeeIdAndDatetimeBetween(empleadoId, startOfDay, endOfDay)
	                                        .stream()
	                                        .filter(cita -> !cita.getCancelled()) // Filtrar solo las citas no canceladas
	                                        .collect(Collectors.toList());

	    // Listado de horarios ocupados
	    Set<LocalTime> horariosOcupados = citasReservadas.stream()
	            .map(cita -> cita.getDatetime().toLocalTime())  // Usar hora de la cita
	            .collect(Collectors.toSet());

	    // Filtrar los horarios disponibles
	    List<String> horariosDisponibles = new ArrayList<>();
	    for (EmployeeSchedule horario : horarios) {
	        LocalTime current = horario.getFromHour();
	        while (current.isBefore(horario.getToHour())) {
	            if (!horariosOcupados.contains(current) && horario.isAvailable()) { // Verificar si el horario está disponible
	                horariosDisponibles.add(current.toString());
	            }
	            current = current.plusHours(1);  // Aquí podemos ajustar según la duración del servicio
	        }
	    }

	    // Crear la respuesta
	    Map<String, Object> response = new HashMap<>();
	    response.put("horariosDisponibles", horariosDisponibles);
	    response.put("mensaje", "Horarios disponibles");
	    response.put("status", "OK");

	    return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> reservarTurno(ReservaTurnoRequest request, Authentication authentication) {
	    // Obtener el email del token
	    String email = authentication.getName();

	    // Buscar el Customer por email
	    Customer customer = customerDao.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Customer no encontrado"));

	    Long customerId = customer.getId(); // Obtener el ID del Customer

	    // Validar que el empleado y el servicio existan
	    Empleado empleado = empleadoDao.findById(request.getEmpleadoId())
	            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

	    Servicio servicio = serviceDao.findById(request.getServicioId())
	            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

	    // Crear la fecha y hora de la cita
	    LocalDateTime fechaHora = LocalDateTime.of(request.getFecha(), LocalTime.parse(request.getHorario()));

	    // Verificar si el horario está disponible
	    List<Cita> citasReservadas = citaDao.findByEmployeeIdAndDatetimeBetween(
	            empleado.getId(),
	            fechaHora.withNano(0).minusMinutes(servicio.getDuration_minutes()),
	            fechaHora.withNano(0).plusMinutes(servicio.getDuration_minutes())
	    ).stream()
	    .filter(cita -> !cita.getCancelled())
	    .collect(Collectors.toList());

	    if (!citasReservadas.isEmpty()) {
	        throw new RuntimeException("Horario no disponible");
	    }

	    // Crear cita
	    Cita nuevaCita = new Cita();
	    nuevaCita.setCustomerId(customerId); // Asignar customerId desde el token
	    nuevaCita.setEmployeeId(empleado.getId());
	    nuevaCita.setServiceId(servicio.getId());
	    nuevaCita.setDatetime(fechaHora);
	    nuevaCita.setEndTime(fechaHora.plusMinutes(servicio.getDuration_minutes()).toLocalTime());

	    citaDao.save(nuevaCita);

	    // Crear la respuesta personalizada
	    Map<String, Object> response = new HashMap<>();
	    response.put("mensaje", "RESUMEN DE CITA");

	    Map<String, Object> detallesCita = new HashMap<>();
	    detallesCita.put("fecha", formatFecha(fechaHora)); // Usar el método formatFecha
	    detallesCita.put("precio", "S/ " + servicio.getPrice());
	    detallesCita.put("servicio", servicio.getName());

	    // Formatear el horario a "hh:mm a"
	    DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("hh:mm a");
	    String horarioFormateado = fechaHora.format(horaFormatter).toUpperCase();
	    detallesCita.put("horario", horarioFormateado);
	    detallesCita.put("duracion", servicio.getDuration_minutes() + " minutos");

	    response.put("cita", detallesCita);
	    response.put("status", "OK");

	    return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Map<String, Object>> cancelarCita(Long citaId) {
	    Map<String, Object> response = new HashMap<>();
	    
	    try {
	        // Buscar la cita por ID
	        Cita cita = citaDao.findById(citaId)
	                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

	        // Verificar si la cita ya está cancelada
	        if (cita.getCancelled()) {
	            throw new RuntimeException("La cita ya fue cancelada previamente");
	        }

	        // Liberar el horario del barbero
	        Long empleadoId = cita.getEmployeeId();
	        LocalTime hora = cita.getDatetime().toLocalTime();
	        
	        citaDao.releaseEmployeeSchedule(empleadoId, hora);

	        // Cambiar el estado de la cita a cancelado
	        cita.setCancelled(true);
	        citaDao.save(cita);

	        // Obtener detalles del servicio
	        Servicio servicio = serviceDao.findById(cita.getServiceId())
	                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

	        // Crear la respuesta con la cita cancelada
	        Map<String, Object> citaDetalles = new HashMap<>();
	        citaDetalles.put("Fecha", cita.getDatetime().toLocalDate().toString());
	        citaDetalles.put("Duración", servicio.getDuration_minutes() + " minutos"); 
	        citaDetalles.put("Servicio", servicio.getName()); 
	        citaDetalles.put("Horario", cita.getDatetime().toLocalTime().toString());
	        citaDetalles.put("estado", "CANCELADA");

	        response.put("citas", List.of(citaDetalles)); // Lista de citas canceladas
	        response.put("mensaje", "Cita cancelada con éxito");
	        response.put("status", "OK");
	        
	        return ResponseEntity.ok(response);

	    } catch (RuntimeException e) {
	        // Manejo de excepciones
	        response.put("mensaje", e.getMessage());
	        response.put("status", "ERROR");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    } catch (Exception e) {
	        // Manejo de cualquier otra excepción inesperada
	        response.put("mensaje", "Error inesperado al cancelar la cita");
	        response.put("status", "ERROR");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	// Formateo de fecha
	private String formatFecha(LocalDateTime fechaHora) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'del' yyyy");
	    return fechaHora.format(formatter);
	}
}
