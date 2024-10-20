package pe.edu.cibertec.serviceImplement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.dto.ServiceDTO;
import pe.edu.cibertec.model.Servicio;
import pe.edu.cibertec.repository.ServicioRepository;
import pe.edu.cibertec.service.ServicioService;

@Service
public class ServicioServiceImplement implements ServicioService{

	@Autowired
	private ServicioRepository serviceRepository;

	@Autowired
    private ModelMapper modelMapper;
	

	@Override
    public ServiceDTO getById(Long id) {
        Optional<Servicio> optionalService = serviceRepository.findById(id);
        if (optionalService.isEmpty()) {
            throw new ResourceNotFoundException("Service with ID " + id + " does not exist");
        }
        return modelMapper.map(optionalService.get(), ServiceDTO.class);
    }

	public List<ServiceDTO> getServicesByEmployeeId (Long id) {
        try{
            List<Servicio> listServices = serviceRepository.findServiceByEmployeesId(id);

            if (!listServices.isEmpty()) {
                return listServices.stream().
                        map(service -> modelMapper.map(service, ServiceDTO.class)).
                        collect(Collectors.toList());
            }
        } catch (ResourceNotFoundException rnf) {
            throw new ResourceNotFoundException("No services founded by employee");
        }

        return null;
    }
	

	
	
}
