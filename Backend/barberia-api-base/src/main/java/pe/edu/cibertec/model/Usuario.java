package pe.edu.cibertec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String username;
	public String password;
	public String email;
	public int role_id;
	
}
