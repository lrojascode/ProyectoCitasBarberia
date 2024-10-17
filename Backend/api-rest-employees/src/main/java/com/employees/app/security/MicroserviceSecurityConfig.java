package com.employees.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MicroserviceSecurityConfig {

    private final JWTAuthenticationFilter jwtAuthorizationFilter;

    public MicroserviceSecurityConfig(JWTAuthenticationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin sesiones
            .and()
            .authorizeRequests()
            .anyRequest().authenticated() // Todas las solicitudes deben estar autenticadas
            .and()
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class); // Agregar filtro JWT

        return http.build();
    }
}
