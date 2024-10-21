package com.example.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfileDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String email;
    private String password;
}