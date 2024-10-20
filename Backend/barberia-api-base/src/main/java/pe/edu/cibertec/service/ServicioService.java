package pe.edu.cibertec.service;

import java.util.List;

import pe.edu.cibertec.dto.ServiceDTO;

public interface ServicioService{
	ServiceDTO getById(Long id);
	List<ServiceDTO> getServicesByEmployeeId (Long id);
}
