package pe.edu.cibertec.service;

import java.time.LocalDate;
import java.util.List;

import pe.edu.cibertec.dto.AppointmentDTO;

public interface AppointmentService {
    List<AppointmentDTO> listAllByCustomerId(Long customerId);

    void cancelById(Long id);

    List<AppointmentDTO> listFreeAppointmentsByEmployeeIdAndServiceIdAndDate(Long employeeId,Long serviceId, LocalDate date);

}
