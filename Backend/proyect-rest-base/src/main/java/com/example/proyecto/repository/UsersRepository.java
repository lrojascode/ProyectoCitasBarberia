package com.example.proyecto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.model.users;


@Repository
public interface UsersRepository extends JpaRepository<users, Long>{
}

