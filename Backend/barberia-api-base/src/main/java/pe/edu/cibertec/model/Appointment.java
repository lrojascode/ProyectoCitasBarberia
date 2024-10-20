package pe.edu.cibertec.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customers_id")
    private Customer customer;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employees_id")
    private Empleado employee;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "services_id")
    private Servicio service;
    
    private LocalDateTime datetime;
    
    private LocalTime endTime;
    @NotNull
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean cancelled;

    public Appointment(Customer customer, Empleado employee, Servicio service, LocalDateTime datetime,
                       LocalTime endTime) {
        this.customer = customer;
        this.employee = employee;
        this.service = service;
        this.datetime = datetime;
        this.endTime = endTime;
        this.cancelled = false;
    }
}
