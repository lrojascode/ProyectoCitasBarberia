package pe.edu.cibertec.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.dto.EmpleadoDTO;
import pe.edu.cibertec.model.Empleado;
import pe.edu.cibertec.model.Servicio;
import pe.edu.cibertec.repository.EmpleadoRepository;
import pe.edu.cibertec.repository.ServicioRepository;
import pe.edu.cibertec.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ServicioRepository ServicioRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Allows to get information about an employee by its id
     * @param id ID of the employee
     * @return EmployeeDTO entity
     */
    @Override
    public EmpleadoDTO getById(Long id) {
        Optional<Empleado> optionalEmployee = empleadoRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
        
        // Mapea el empleado a DTO sin incluir servicios
        EmpleadoDTO empleadoDTO = modelMapper.map(optionalEmployee.get(), EmpleadoDTO.class);
        
        // Asegúrate de que la lista de servicios sea null
        empleadoDTO.setServices(null);
        
        return empleadoDTO;
    }

    @Override
    public EmpleadoDTO getEmployeeServices(Long employeeId) {
        Empleado employee = null;
        Optional<Empleado> employeeOpt = empleadoRepository.findById(employeeId);

        if (employeeOpt.isPresent()) {
            employee = employeeOpt.get();

            // Obtiene la lista de servicios asociados al empleado
            List<Servicio> listServices = ServicioRepository.findServiceByEmployeesId(employee.getId());

            // Si hay servicios, se asignan al DTO
            if (!listServices.isEmpty()) {
                employee.setServices(listServices);
            }
        }

        // Mapea el empleado a DTO incluyendo los servicios
        return modelMapper.map(employee, EmpleadoDTO.class);
    }
}
