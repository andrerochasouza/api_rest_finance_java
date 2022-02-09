package br.com.finance.cdd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.finance.cdd.data.DetailAdminData;
import br.com.finance.cdd.model.Admin;
import br.com.finance.cdd.repository.AdminRepository;

@Component
public class DetailAdminServiceImpl implements UserDetailsService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> admin = adminRepository.findByLogin(username);
		
		if(admin.isEmpty()) {
			throw new UsernameNotFoundException("Not found by Login: " + username);
		}
		
		
		return new DetailAdminData(admin);
	}

}
