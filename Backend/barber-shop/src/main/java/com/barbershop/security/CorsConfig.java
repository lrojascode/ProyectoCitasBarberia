package com.barbershop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login")
                    .allowedOrigins(
                        "http://localhost:4200",           // Para desarrollo local
                        "https://barberia-app17-*.vercel.app", // Tu app en Vercel
                        "https://*.vercel.app"             // Cualquier subdominio de Vercel
                    )
                    .allowedMethods("*")
                    .allowCredentials(true)               // Importante para autenticación
                    .exposedHeaders("*");
                
                registry.addMapping("/api/**")
                    .allowedOrigins(
                        "http://localhost:4200",           // Para desarrollo local
                        "https://barberia-app17-*.vercel.app", // Tu app en Vercel
                        "https://*.vercel.app"             // Cualquier subdominio de Vercel
                    )
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowCredentials(true)               // Importante para autenticación
                    .allowedHeaders("*");
            }
        };
    }
}
