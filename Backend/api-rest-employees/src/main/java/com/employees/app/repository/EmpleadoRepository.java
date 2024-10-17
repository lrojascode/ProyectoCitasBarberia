package com.employees.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employees.app.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	List<Empleado> findAllByActive(Boolean active);
}
