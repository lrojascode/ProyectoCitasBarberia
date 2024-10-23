package com.barbershop.model;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "appointments")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("customers_id") 
    @Column(name = "customers_id", nullable = false)
    private Long customerId;

    @JsonProperty("employees_id") 
    @Column(name = "employees_id", nullable = false)
    private Long employeeId;

    @JsonProperty("services_id") 
    @Column(name = "services_id", nullable = false)
    private Long serviceId;

    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "HH:mm")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @JsonProperty("cancelled")
    @Column(nullable = false)
    private Boolean cancelled = false;
    
}
