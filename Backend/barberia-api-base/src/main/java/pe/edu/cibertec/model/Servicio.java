package pe.edu.cibertec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
}
