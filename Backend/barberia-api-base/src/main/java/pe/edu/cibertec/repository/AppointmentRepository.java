package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.model.Appointment;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value = "SELECT ea " +
            " FROM Appointment ea " +
            " WHERE ea.service.id IN :serviceIds " +
            " AND MONTH(ea.datetime) = :month AND YEAR(ea.datetime) = :year ")
    List<Appointment> findByServiceIdsAndYearAndMonth(List<Long> serviceIds, int year, int month);

    @Query(value = "SELECT ea " +
            " FROM Appointment ea " +
            " WHERE ea.service.id IN :serviceIds AND ea.customer.id = :customerId")
    List<Appointment> findByCustomerIdAndServiceIds(List<Long> serviceIds, Long customerId);

    @Query(value = "SELECT ea " +
        " FROM Appointment ea " +
        " WHERE ea.employee.id = :employeeId " +
        " AND ea.service.id = :serviceId " +
        " AND DATE(ea.datetime) = :date AND ea.cancelled = false")
List<Appointment> findByEmployeeIdAndServiceIdAndDate(Long employeeId, Long serviceId, @Param("date") java.sql.Date date);



    List<Appointment> findByCustomerId(Long customerId);
}
