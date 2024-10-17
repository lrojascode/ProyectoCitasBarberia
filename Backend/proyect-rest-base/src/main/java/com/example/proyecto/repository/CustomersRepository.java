package com.example.proyecto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.model.customers;


@Repository
public interface CustomersRepository extends JpaRepository<customers, Long>{
}
