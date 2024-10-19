package pe.edu.cibertec.serviceImplement;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import pe.edu.cibertec.model.Usuario;

@AllArgsConstructor
public class UserDetailImplement implements UserDetails{
	
	private final Usuario usuario;

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getAuthorities(); // Usa las autoridades del usuario
    }

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getEmail() {
		return usuario.getEmail();
	}

}
