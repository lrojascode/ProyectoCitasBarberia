package pe.edu.cibertec.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "services")
public class Servicio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String name;
	public String description;
	public double price;
	public int duration_minutes;

	@ManyToMany
    @JoinTable(name = "employees_services",
            joinColumns = @JoinColumn(name = "services_id"),
            inverseJoinColumns = @JoinColumn(name = "employees_id"))
    private List<Empleado> employees;

	// Constructor vacío requerido por Hibernate
    public Servicio() {
    }

    public Servicio(String name, String description, Double price, Integer duration_minutes) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration_minutes = duration_minutes;
    }
	
}
