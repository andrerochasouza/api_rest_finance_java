package br.com.finance.cdd.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.finance.cdd.model.Admin;

public class DetailAdminData implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3029217426455672576L;

	private final Optional<Admin> admin;
	
	public DetailAdminData(Optional<Admin> admin) {
		this.admin = admin;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return admin.orElse(new Admin()).getPass();
	}

	@Override
	public String getUsername() {
		return admin.orElse(new Admin()).getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
