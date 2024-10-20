package pe.edu.cibertec.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.istack.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private Usuario user;
    @NotNull 
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private String phone;

    public Customer(Usuario user, String firstName, String lastName, String phone) {
        this.user = user;
        this.first_name = firstName;
        this.last_name = lastName;
        this.phone = phone;
    }

    /**
     * Customer instance without User data
     * @param id
     * @param firstName
     * @param lastName
     * @param phone
     */
    public Customer(Long id, String firstName, String lastName, String phone) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.phone = phone;
    }
}
