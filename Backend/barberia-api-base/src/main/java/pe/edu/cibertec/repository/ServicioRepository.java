package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{

}
