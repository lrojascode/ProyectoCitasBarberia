package com.barbershop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbershop.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	
	List<Empleado> findAllByActive(Boolean active);
	
}
