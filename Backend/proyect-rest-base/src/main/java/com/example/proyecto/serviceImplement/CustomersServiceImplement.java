package com.example.proyecto.serviceImplement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.proyecto.model.customers;
import com.example.proyecto.repository.CustomersRepository;
import com.example.proyecto.service.CustomersService;

@Service
public class CustomersServiceImplement implements CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listarCustomers() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("customers", customersRepository.findAll());
            response.put("message", "Customers retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error retrieving customers");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarCustomersPorId(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<customers> customer = customersRepository.findById(id);
            if (customer.isPresent()) {
                response.put("customer", customer.get());
                response.put("message", "Customer found successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Customer not found");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error retrieving customer");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarCustomers(customers customer) {
        Map<String, Object> response = new HashMap<>();
        try {
            customers newCustomer = customersRepository.save(customer);
            response.put("customer", newCustomer);
            response.put("message", "Customer added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error adding customer");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarCustomers(customers customer, Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<customers> customerData = customersRepository.findById(id);
            if (customerData.isPresent()) {
                customers existingCustomer = customerData.get();
                existingCustomer.setFirst_name(customer.getFirst_name());
                existingCustomer.setLast_name(customer.getLast_name());
                existingCustomer.setPhone(customer.getPhone());
                existingCustomer.setUsers_id(customer.getUsers_id());
                customers updatedCustomer = customersRepository.save(existingCustomer);
                response.put("customer", updatedCustomer);
                response.put("message", "Customer updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Customer not found");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error updating customer");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarCustomers(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            customersRepository.deleteById(id);
            response.put("message", "Customer deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error deleting customer");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
