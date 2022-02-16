package br.com.finance.cdd.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.finance.cdd.service.DetailAdminServiceImpl;

@EnableWebSecurity
public class JWTConfig extends WebSecurityConfigurerAdapter{

	
	private final DetailAdminServiceImpl adminService;
	private final PasswordEncoder passwordEncoder;
	
	public JWTConfig(DetailAdminServiceImpl adminService, PasswordEncoder passwordEncoder) {
		this.adminService = adminService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(adminService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
								.antMatchers(HttpMethod.POST, "/login", "/new-admin").permitAll()
								.antMatchers(HttpMethod.GET, "/is-valid-login").permitAll()
								.anyRequest().authenticated()
								.and()
								.cors()
								.and()
								.addFilter(new JWTAutenticarFilter(authenticationManager()))
								.addFilter(new JWTValidarFilter(authenticationManager()))
								.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
