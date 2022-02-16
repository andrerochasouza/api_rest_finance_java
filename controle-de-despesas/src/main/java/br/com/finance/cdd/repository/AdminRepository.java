package br.com.finance.cdd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	public Optional<Admin> findByLogin(String login);

	public Optional<Admin> findByEmail(String email);
}
