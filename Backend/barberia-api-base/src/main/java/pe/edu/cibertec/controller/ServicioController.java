package pe.edu.cibertec.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.dto.ServiceDTO;
import pe.edu.cibertec.service.ServicioService;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

	@Autowired
	private ServicioService servicesService;
	
	/**
     * Allows to get a service by its id
     *
     * @param serviceId ID of service
     * @return ServiceDTO entity
     */
    @GetMapping("/services/{service_id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable("service_id") Long serviceId) {
        return new ResponseEntity<ServiceDTO>(servicesService.getById(serviceId), HttpStatus.OK);
    }
	
}
