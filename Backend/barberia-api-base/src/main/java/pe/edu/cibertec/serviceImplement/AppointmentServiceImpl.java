package pe.edu.cibertec.serviceImplement;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.dto.AppointmentDTO;
import pe.edu.cibertec.exception.AppointmentException;
import pe.edu.cibertec.model.Appointment;
import pe.edu.cibertec.model.Empleado;
import pe.edu.cibertec.model.EmployeeSchedule;
import pe.edu.cibertec.model.Servicio;
import pe.edu.cibertec.model.Workable;
import pe.edu.cibertec.repository.AppointmentRepository;
import pe.edu.cibertec.repository.CustomerRepository;
import pe.edu.cibertec.repository.EmpleadoRepository;
import pe.edu.cibertec.repository.EmployeeScheduleRepository;
import pe.edu.cibertec.repository.ServicioRepository;
import pe.edu.cibertec.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {


    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    EmpleadoRepository employeeRepository;

    @Autowired
    EmployeeScheduleRepository employeeScheduleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ServicioRepository serviceRepository;

    @Autowired
    ModelMapper modelMapper;

    

     @Override
    public List<AppointmentDTO> listAllByCustomerId(Long customerId) {
        boolean customerExist = customerRepository.existsById(customerId);
        if (!customerExist) {
            throw new ResourceNotFoundException("Customer with ID " + customerId + " not found");
        }
        List<Appointment> appointmentList = appointmentRepository
                .findByCustomerId(customerId);
        if (appointmentList.isEmpty()) {
            throw new ResourceNotFoundException("Customer with ID " + customerId + " has no appointments yet");
        }
        return appointmentList.stream()
        .map(employeesAppointment -> modelMapper.map(employeesAppointment, AppointmentDTO.class))
        .collect(Collectors.toList());  // Usar Collectors.toList() en lugar de .toList()
    }

    @Override
    public void cancelById(Long id) {
        Optional<Appointment> optionalEmployeesAppointments = appointmentRepository.findById(id);
        Appointment appointment;
        if (optionalEmployeesAppointments.isPresent()) {
            appointment = optionalEmployeesAppointments.get();
            appointment.setCancelled(true);
        } else {
            throw new ResourceNotFoundException("Appointment with ID " + id + " not found");
        }
        appointmentRepository.save(appointment);
    }

    

    private List<DayOfWeek> getWorkingDaysListOfWorkable(Workable workable) {
        List<DayOfWeek> workingDaysList = new ArrayList<>();
        for (char letter : workable.getWorkingDays().toCharArray()) {
            switch (letter) {
                case 'L':
                    workingDaysList.add(DayOfWeek.MONDAY);
                    break;
                case 'M':
                    workingDaysList.add(DayOfWeek.TUESDAY);
                    break;
                case 'X':
                    workingDaysList.add(DayOfWeek.WEDNESDAY);
                    break;
                case 'J':
                    workingDaysList.add(DayOfWeek.THURSDAY);
                    break;
                case 'V':
                    workingDaysList.add(DayOfWeek.FRIDAY);
                    break;
                case 'S':
                    workingDaysList.add(DayOfWeek.SATURDAY);
                    break;
                case 'D':
                    workingDaysList.add(DayOfWeek.SUNDAY);
                    break;
                default:
                    break;
            }
        }
        return workingDaysList;
    }

    @Override
    public List<AppointmentDTO> listFreeAppointmentsByEmployeeIdAndServiceIdAndDate(Long employeeId, Long serviceId, LocalDate date) {
        // Convierte LocalDate a java.sql.Date
        Date sqlDate = Date.valueOf(date);
        
        List<Appointment> appointmentList = appointmentRepository.findByEmployeeIdAndServiceIdAndDate(employeeId, serviceId, sqlDate);
        
        if (appointmentList.isEmpty()) {
            throw new ResourceNotFoundException("No hay citas ocupadas para el empleado ID " + employeeId + " y servicio ID " + serviceId + " en la fecha " + date);
        }
    
        return appointmentList.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }


    
}
