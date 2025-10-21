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
                        "http://localhost:4200",                    // Desarrollo local
                        "https://barberia-app17.vercel.app"         // Tu app específica
                    )
                    .allowedOriginPatterns(
                        "https://barberia-app17-*.vercel.app",      // Preview deployments
                        "https://*.vercel.app"                      // Otros subdominios
                    )
                    .allowedMethods("POST", "OPTIONS")
                    .allowCredentials(true)
                    .allowedHeaders("*")
                    .exposedHeaders("*");
                
                registry.addMapping("/api/**")
                    .allowedOrigins(
                        "http://localhost:4200",                    // Desarrollo local
                        "https://barberia-app17.vercel.app"         // Tu app específica
                    )
                    .allowedOriginPatterns(
                        "https://barberia-app17-*.vercel.app",      // Preview deployments
                        "https://*.vercel.app"                      // Otros subdominios
                    )
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowCredentials(true)
                    .allowedHeaders("*");
            }
        };
    }
}
