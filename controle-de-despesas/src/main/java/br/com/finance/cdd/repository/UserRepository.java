package br.com.finance.cdd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByName(String name);
	
	public Optional<User> findByCpf(String cpf);

	public List<User> findAllByDateDeleteIsNull(Pageable pageable);

	@Query(value = "SELECT count(cpf) FROM FINANCE_IO.USERS u WHERE u.cpf = ?1",
		   nativeQuery = true)
	Integer findCountCpf(String cpf);
}
