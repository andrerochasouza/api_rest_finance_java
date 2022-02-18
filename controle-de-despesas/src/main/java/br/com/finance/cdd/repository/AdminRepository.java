package br.com.finance.cdd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	public Optional<Admin> findByLogin(String login);
	
	
	@Query(value = "SELECT name FROM FINANCE_IO.ADMIN u WHERE u.login = ?1",
	           nativeQuery = true)
	public String findByLogin_ReturnName(String login);

}
