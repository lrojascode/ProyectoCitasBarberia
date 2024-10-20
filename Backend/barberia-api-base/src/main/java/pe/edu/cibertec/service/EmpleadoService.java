package pe.edu.cibertec.service;

import pe.edu.cibertec.dto.EmpleadoDTO;

public interface EmpleadoService {

    EmpleadoDTO getById(Long id);

    EmpleadoDTO getEmployeeServices(Long employeeId);
    
}
