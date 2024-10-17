package pe.edu.cibertec.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.cibertec.model.Auth;
import pe.edu.cibertec.serviceImplement.UserDetailImplement;
import pe.edu.cibertec.util.Token;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		Auth authCredenciales = new Auth();
		
		try {
			authCredenciales = new ObjectMapper().readValue(request.getReader(), Auth.class);
		} catch (Exception e) {
		}
		
		UsernamePasswordAuthenticationToken userPAT = new UsernamePasswordAuthenticationToken(
				authCredenciales.getUsername(),
				authCredenciales.getPassword(),
				Collections.emptyList()
				);
		
		return getAuthenticationManager().authenticate(userPAT);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserDetailImplement userDetails = (UserDetailImplement) authResult.getPrincipal();
		String token = Token.crearToken(userDetails.getEmail(), userDetails.getUsername());
		
		response.addHeader("Authorization", "Bearer "+ token);
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}

}
