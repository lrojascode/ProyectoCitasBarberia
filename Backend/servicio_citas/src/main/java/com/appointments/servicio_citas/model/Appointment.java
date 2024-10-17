package com.appointments.servicio_citas.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customers_id", nullable = false)
    private Long customerId;

    @Column(name = "employees_id", nullable = false)
    private Long employeeId;

    @Column(name = "services_id", nullable = false)
    private Long serviceId;

    @Column(nullable = false)
    private Timestamp datetime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    @Column(nullable = false)
    private Boolean cancelled = false;
}

