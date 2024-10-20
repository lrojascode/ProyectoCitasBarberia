package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //Find a customer by username from user entity
    @Query("SELECT c FROM Customer c " +
           "JOIN c.user u " +  // Cambiado de INNER JOIN User u
           "WHERE u.username = :username")
    Customer findCustomerByUsername(@Param("username") String username);

    //Find a customer by email from user entity
    @Query("SELECT c FROM Customer c " +
           "JOIN c.user u " +  // Cambiado de INNER JOIN User u
           "WHERE u.email = :email")
    Customer findCustomerByEmail(@Param("email") String email);

    Optional<Customer> findCustomerByUserId(Long userId);
}
