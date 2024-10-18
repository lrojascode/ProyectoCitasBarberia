package pe.edu.cibertec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class Empleado {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String first_name;
	private String last_name;
	private String profession;
	private String picture;
    @NotNull
    @ManyToMany
    @JoinTable(name = "employees_services",
            joinColumns = @JoinColumn(name = "employees_id"),
            inverseJoinColumns = @JoinColumn(name = "services_id"))
    private List<Servicio> services;
    @NotNull
    private String workingDays;
    @NotNull
    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean active;

    public Empleado(String firstName, String lastName, String profession, String picture, List<Servicio> services,
                    String workingDays) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.profession = profession;
        this.picture = picture;
        this.services = services;
        this.workingDays = workingDays;
        this.active = true;
    }
}
