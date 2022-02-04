package br.com.finance.cdd.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.finance.cdd.data.DetailAdminData;
import br.com.finance.cdd.model.Admin;


public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter{

	// Precisa mudar para o arquivo de configurações (aplication.properties)
	public static final int TOKEN_EXPIRACAO = 600_000;
	
	public static final String TOKEN_SENHA = "27d43afd-6755-4753-8d87-c0bd3b4fe2b3";
	
	private final AuthenticationManager authenticationManager;
	
	public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			Admin admin = new ObjectMapper()
							.readValue(request.getInputStream(), Admin.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					admin.getLogin(),
					admin.getPass(),
					new ArrayList<>()
					));
		} catch (IOException e) {
			throw new RuntimeException("Error to Authenticated admin: " + e);
		}
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response, 
											FilterChain chain,
											Authentication authResult) 
											throws IOException, ServletException {
		
		DetailAdminData adminData = (DetailAdminData) authResult.getPrincipal();
		
		String token = JWT.create()
				.withSubject(adminData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));
		
		response.getWriter().write(token);
		response.getWriter().flush();
	}
}
