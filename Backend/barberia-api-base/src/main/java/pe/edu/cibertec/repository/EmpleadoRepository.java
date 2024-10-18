package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
