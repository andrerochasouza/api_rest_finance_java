package br.com.finance.cdd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByName(String name);
	
	public Optional<User> findByCpf(String cpf);

	@Query(value = "SELECT * FROM FINANCE_IO.USERS u " +
            "WHERE u.id_admin_user = ?1" +
            " && u.date_delete is null",
    nativeQuery = true)
	Page<User> findByUsersPage(Long id_admin_user, Pageable pageable);
 
	@Query(value = "SELECT count(cpf) FROM FINANCE_IO.USERS u WHERE u.cpf = ?1",
		   nativeQuery = true)
	Integer findCountCpf(String cpf);
	
	@Query(value = "SELECT u.* FROM finance_io.users AS u, finance_io.aplications AS a WHERE a.date_init > ?1 AND a.date_init < ?2",
			   nativeQuery = true)
	Optional<List<User>> findListUserDayHighestWallet(String dateInit, String dateAfter);
	
	@Query(value = "SELECT COUNT(*) FROM finance_io.users WHERE users.date_delete is null AND users.id_admin_user = ?1",
			   nativeQuery = true)
	Optional<Integer> findMaxUsersCount(Long idAdmin);
	
	@Query(value = "SELECT * FROM finance_io.users WHERE users.id_admin_user = ?1",
			   nativeQuery = true)
	Optional<List<User>> findAllUsersByIdAdminIs(Long idAdmin);
}
