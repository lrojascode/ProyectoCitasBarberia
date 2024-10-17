package com.employees.app.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final String TOKEN_SECRETO = "aLg3eqbV2S4pZd9AFiMh4mAcRAt1Y0Jb"; // Clave secreta para firmar/verificar el token

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        // Verifica que el token esté presente y comience con "Bearer "
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token ausente o mal formado.");
            return;
        }

        String token = bearerToken.substring(7); // Extraer el token sin "Bearer "

        try {
            // Validar y extraer datos del token
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(TOKEN_SECRETO.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();

            // Si el token es válido, crea una autenticación
            if (username != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establece la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (JwtException e) {
            // Si el token es inválido, limpiar el contexto de seguridad y devolver 401
            SecurityContextHolder.clearContext();
            enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado.");
            return;
        }

        // Si todo está bien, continúa con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    // Método para enviar una respuesta de error en formato JSON
    private void enviarError(HttpServletResponse response, int statusCode, String mensajeError) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + mensajeError + "\"}");
        response.getWriter().flush();
    }
}



