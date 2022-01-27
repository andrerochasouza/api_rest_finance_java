package br.com.finance.cdd.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByName(String name);

	public Page<User> findAll(Pageable pageable);
	
}
