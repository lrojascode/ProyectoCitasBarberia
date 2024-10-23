package com.barbershop.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_schedules")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Empleado empleado;

    @Column(name = "from_hour")
    private LocalTime fromHour;

    @Column(name = "to_hour")
    private LocalTime toHour;
    
    @Column(name = "available")
    private boolean available;
    
}
